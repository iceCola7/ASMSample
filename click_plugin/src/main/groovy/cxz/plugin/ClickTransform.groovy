package cxz.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.cxz.click_plugin.ClickClassVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter

import java.util.jar.JarFile

public class ClickTransform extends Transform {

    /**
     * 设置名字
     */
    @Override
    public String getName() {
        return "ClickTransform"
    }

    /**
     * 用于过滤文件类型。填什么类型，就把该类型的全部文件返回。默认有以下两种类型：
     * QualifiedContent.DefaultContentType.CLASSES：class 文件类型。
     * QualifiedContent.DefaultContentType.RESOURCES：资源文件类型。
     */
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * getScopes()：用于规定检索的范围：
     * QualifiedContent.Scope.PROJECT：主 Project。
     * QualifiedContent.Scope.SUB_PROJECTS：其它 Module。
     * QualifiedContent.Scope.EXTERNAL_LIBRARIES：外部库。
     * QualifiedContent.Scope.TESTED_CODE：当前变量的测试代码，包含依赖库。
     * QualifiedContent.Scope.PROVIDED_ONLY：本地或远程的依赖项。
     * QualifiedContent.Scope.PROJECT_LOCAL_DEPS：主 Project 的本地依赖项，包含本地 jar，已废弃，使用QualifiedContent.Scope.EXTERNAL_LIBRARIES代替
     * QualifiedContent.Scope.SUB_PROJECTS_LOCAL_DEPS：其它 Module 的本地依赖项，包含本地 jar，已废弃，使用QualifiedContent.Scope.EXTERNAL_LIBRARIES代替
     */
    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 是否支持增量编译。
     */
    @Override
    public boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        // super.transform(transformInvocation)
        //获取输入项进行遍历
        def transformInputs = transformInvocation.inputs
        //获取输出目录
        def transformOutputProvider = transformInvocation.outputProvider
        transformInputs.each { TransformInput transformInput ->
            //遍历 jar 包
            transformInput.jarInputs.each { JarInput jarInput ->
                //println("jarInput：" + jarInput)
                //使用 JarFile 进行解压
//                def enumeration = new JarFile(jarInput.file).entries()
//                while (enumeration.hasMoreElements()) {
//                    //获取 jar 里面的内容
//                    def entry = enumeration.nextElement()
//                    println("jarInput File：" + entry.name)
//                }
                //直接将 jar 包 copy 到输出目录
                File dest = transformOutputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
            //遍历目录
            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                //println("directoryInputs：" + directoryInput)
                //获取目录里面的 class 文件
                directoryInput.file.eachFileRecurse { File file ->
                    //println("directoryInputs File：" + file.name)
                    if (file.absolutePath.endsWith(".class")) {
                        //对于class文件进行读取解析
                        def classReader = new ClassReader(file.bytes)
                        //将class文件内容写入到ClassWriter中
                        def classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        //使用ClickClassVisitor去读取内容
                        def classVisitor = new ClickClassVisitor(classWriter)
                        //开始读取
                        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                        //获取修改后的内容
                        def bytes = classWriter.toByteArray()
                        //覆盖之前的文件
                        def outputStream = new FileOutputStream(file.path)
                        outputStream.write(bytes)
                        outputStream.close()
                    }
                }
                //将 Directory 的文件 copy 到输出目录
                File dest = transformOutputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
        }
    }
}
