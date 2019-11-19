log
1.简介（1.0-SNAPSHOT）
文件系统记录的日志信息，日志信息中包含日志等级、模块信息、组件信息、序号、描述
提供可供装饰的Log基类（LogBase）和已封装好的日志类（LogB）


LogBase的装饰类，用来装饰对象（logMsg）数据
1.LogDecoratorDate:前增加日期信息 格式yyyy-MM-dd 后面有一个空格
2.LogDecoratorTime:前增加时间信息 格式HH:mm:ss 后面有一个空格
3.LogDecoratorDateTime:前增加日期时间信息 格式yyyy-MM-dd HH:mm:ss.SSS 后面有一个空格;
						支持自己设置输出的时间格式,示例如 LogBase logBase = new LogDecoratorDateTime(logBase,pattern)
4.LogDecoratorLevel:增加日志标识,组装成String格式形如[D00.000.00000]
5.LogDecoratorPrefix:前增加信息
6.LogDecoratorSuffix:后增加信息


LogB使用方法
配置文件mylog.properties,需要放在项目resources文件夹中,名字不能有误
识别信息：level、DEBUG、INFO、WARN、ERROR、FATAL
#level 为输出等级 ALL为全部打开（默认） OFF为全部关闭
#FATAL+输出大于等于FATAL等级的日志（适用其他等级）
#FATAL-输出小于等于FATAL等级的日志（适用其他等级）
#输出等级范围 ALL > OFF > FATAL(-) > ERROR(±) > WARN(±) > INFO(±) > DEBUG(+)
#不支持+和-同时使用，按照输出等级，等级高的优先生效
#例:level=WARN+,INFO-   WARN+生效
#其他为对应等级的输出路径（路径需要有效）
配置文件若不存在则加载默认的配置文件，默认文件内容如下：
level=ALL
DEBUG=./ReadMe.txt
INFO=./ReadMe.txt
WARN=./ReadMe.txt
ERROR=./ReadMe.txt
FATAL=./ReadMe.txt

使用log(String msg, String level, String mN, String cN, int eN)输出日志信息
msg为日志信息
mN为模块信息（若可转化为数字，则2位有效）
cN为组件信息（若可转化为数字，则3位有效）
eN为序号（5位有效）
组装成 DateTime [Level.mN.cN.eN]msg的格式