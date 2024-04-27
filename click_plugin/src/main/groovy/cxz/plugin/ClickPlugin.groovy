package cxz.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class ClickPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        println("配置成功--------->ClickPlugin")
        project.android.registerTransform(new ClickTransform())
    }
}
