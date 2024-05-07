package cxz.auto_track

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MkAnalyticsPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        println("配置成功--------->MkAnalyticsPlugin")

        MkAnalyticsExtension extension = project.extensions.create("MkAnalytics", MkAnalyticsExtension)

        boolean disableSensorsAnalyticsPlugin = false
        Properties properties = new Properties()
        if (project.rootProject.file('gradle.properties').exists()) {
            properties.load(project.rootProject.file('gradle.properties').newDataInputStream())
            disableSensorsAnalyticsPlugin = Boolean.parseBoolean(properties.getProperty("sensorsAnalytics.disablePlugin", "false"))
        }

        if (!disableSensorsAnalyticsPlugin) {
            AppExtension appExtension = project.extensions.findByType(AppExtension.class)
            appExtension.registerTransform(new MkAnalyticsTransform(project, extension))
        } else {
            println("------------您已关闭了插件--------------")
        }
    }
}