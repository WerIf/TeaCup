//使用指南

//在自定义注解的时候
@Target元注解用来表明我们注解可以出现的地方，参数是一个ElementType类型的数组，所以@Target可以设置注解同时出现在多个地方。比如既可以出现来类的前面也可以出现在变量的前面。
@Target元注解ElementType枚举(用来指定注解可以出现的地方):
@Target-ElementType类型       说明
ElementType.Type              接口,类,枚举,注解
ElementType.FIELD             字段,枚举的常量
ElementType.METHOD            方法
ElementType.PARAMETER         方法参数
ElementType.CONSTRUCTOR       构造函数
ElementType.LOCAL_VARIABLE    局部变量
ElementType.ANNOTATION_TYPE   注解
ElementType.PACKAGE           包

@Retention
@Retention表示需要在什么级别保存该注释信息，用于描述注解的生命周期(即：被描述的注解在什么范围内有效)。参数是RetentionPolicy枚举对象。
RetentionPolicy的枚举类型有(默认值为Class)
@Retention-RetentionPolicy类型  说明
RetentionPolicy.SOURCE          注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃
RetentionPolicy.CLASS           注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
RetentionPolicy.RUNTIME         注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在