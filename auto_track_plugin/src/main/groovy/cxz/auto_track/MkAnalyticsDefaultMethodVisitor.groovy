package cxz.auto_track

import groovyjarjarasm.asm.AnnotationVisitor
import groovyjarjarasm.asm.Attribute
import groovyjarjarasm.asm.Label
import groovyjarjarasm.asm.MethodVisitor
import groovyjarjarasm.asm.commons.AdviceAdapter
import org.objectweb.asm.Opcodes

class MkAnalyticsDefaultMethodVisitor extends AdviceAdapter {

    MkAnalyticsDefaultMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
        super(Opcodes.ASM6, mv, access, name, desc)
    }

    /**
     * 表示 ASM 开始扫描这个方法
     */
    @Override
    void visitCode() {
        super.visitCode()
    }

    @Override
    void visitMethodInsn(int opcode, String owner, String name, String desc) {
        super.visitMethodInsn(opcode, owner, name, desc)
    }

    @Override
    void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute)
    }

    /**
     * 表示方法输出完毕
     */
    @Override
    void visitEnd() {
        super.visitEnd()
    }

    @Override
    void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc)
    }

    @Override
    void visitIincInsn(int var, int increment) {
        super.visitIincInsn(var, increment)
    }

    @Override
    void visitIntInsn(int i, int i1) {
        super.visitIntInsn(i, i1)
    }

    /**
     * 该方法是 visitEnd 之前调用的方法，可以反复调用。用以确定类方法在执行时候的堆栈大小。
     * @param maxStack
     * @param maxLocals
     */
    @Override
    void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals)
    }

    @Override
    void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var)
    }

    @Override
    void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label)
    }

    @Override
    void visitLookupSwitchInsn(Label label, int[] ints, Label[] labels) {
        super.visitLookupSwitchInsn(label, ints, labels)
    }

    @Override
    void visitMultiANewArrayInsn(String s, int i) {
        super.visitMultiANewArrayInsn(s, i)
    }

    @Override
    void visitTableSwitchInsn(int i, int i1, Label label, Label[] labels) {
        super.visitTableSwitchInsn(i, i1, label, labels)
    }

    @Override
    void visitTryCatchBlock(Label label, Label label1, Label label2, String s) {
        super.visitTryCatchBlock(label, label1, label2, s)
    }

    @Override
    void visitTypeInsn(int opcode, String s) {
        super.visitTypeInsn(opcode, s)
    }

    @Override
    void visitLocalVariable(String s, String s1, String s2, Label label, Label label1, int i) {
        super.visitLocalVariable(s, s1, s2, label, label1, i)
    }

    @Override
    void visitInsn(int opcode) {
        super.visitInsn(opcode)
    }

    @Override
    AnnotationVisitor visitAnnotation(String s, boolean b) {
        return super.visitAnnotation(s, b)
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter()
    }

    /**
     * 使用 onMethodExit 这样就不会影响到应用程序原有点击事件的响应速度
     * @param opcode
     */
    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode)
    }
}