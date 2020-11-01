# Ares 
> 应用名字来源于古希腊战争之神阿瑞斯 Ares

## 技术栈
RestAssured/TestNG/Hamcrest/Allure

框架采用Java、RestAssured、TestNG、Hamcrest等主流备受好评的库搭建。

Rest-Assured 是一个轻量级的REST API 客户端，可以直接编写代码向服务器端发起HTTP 请求，并验证返回结果；它的语法非常简洁，在他的基础上我们又做了一层对get、post、put、delete请求各种传参场景下的封装，使得测试脚本编写人员更方便的发起HTTP请求。

TestNG是新一代的测试框架，让开发者和测试者能够通过简单的注解、分组、指定顺序、参数化就可以编写更加灵活、更加强大的测试用例。对于测试套件的组装，用例等级，并发执行等，也都提供了支持，特别方便进行持续集成测试。尤其是@DataProvider注解，更是提供了进行数据驱动测试的能力，使得我们可以一个接口只需编写一个脚本，可以完成所有用例的测试。

Hamcrest弥补了TestNG断言的不足，其自带和提供了自定义匹配器的能力，使得对测试结果的断言可以更加灵活。

## 支持特性
- 数据驱动简化测试代码量
	- 一个接口只需编写一个测试脚本对应，可以用于执行多条用例
- 用例维护的方便性
	- 在excel文件中维护测试用例，多行数据即多个用例
- 测试用例执行的灵活性
	- 测试套件可以灵活配置需要执行的用例，并可以配置执行的并发方式提升测试效率
- 提供强大而全面的断言
    - TestNG断言，Json schema匹配，Hamcrest断言与自定义匹配器等
- 提供多种登录认证方式的抽象接口，便于测试前置进行登录实现
- 支持yaml配置的读取，方便统一配置与读取
- 集成Allure框架自动生成测试报告
- ~~支持多项目多环境区分~~
    - ~~考虑实践中大多测试是一个测试工程对应一个项目，且测试数据只有一套所以框架中暂时去除多项目多环境支持，如有需要可以自己将源码中注释掉的代码打开，注意，多项目多环境的项目目录结构有所不同，详情看代码。~~
- 命令式执行与入参便于持续集成与测试

TODO：
- 支持直连DB查询结果与接口返回结果校验
- 支持文件上传接口的请求
- 场景化的测试
- 监听测试过程测试数据落数据库

## 使用方法
将此项目 mvn install 打包成jar包发布到私有仓库供测试工程引用，或直接在 src/test/ 下按照如下结构说明编写测试脚本。当然，使用jar包的方式的测试工程使用同样的目录结构。

### 结构说明
src/main/ 						下用于框架层代码编写与配置的放置

src/test/                       下才是编写放置测试相关代码文件的地方

src/test/java/api               下编写单接口测试脚本

src/test/java/scenario          下编写场景测试脚本

src/test/resources/env.yml      下编写项目配置信息

src/test/resources/data         下编写excel文件保存的测试数据

src/test/resources/suite        下编写测testng试套件文件组织测试用例集

### 命名约定
使用exel数据源的情况下：
- 单接口测试类注解值与execl文件名相同
- 测试类中测试方法名与excel中sheet表名相同
- ~~项目文件夹名和yml配置中项目名对应~~

### 接口测试工程示例
仓库：https://github.com/tangyiming/ares-example
    
## 测试运行方式
### 命令行
执行项目下所有测试：`mvn clean test`

执行项目所有模块测试：`mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml`

执行单项目某模块测试：`mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/module-testng.xml`

###  ~~运行时指定环境变量用于项目区分执行环境~~
> ~~-Denvironment=dev/beta/prod, 不指定默认beta测试环境~~

~~例如prod生产环境: mvn clean test -Denvironment=prod~~

## 测试报告生成与查看
请先安装配置好allure工具，执行测试自动产生测试报告数据。

使用allure服务查看报告；

方式1 生成报告静态文件并打开index.html查看；
`allure generate ./allure-results/ -o ./reports/ --clean`

方式2 使用allure服务展示报告：
`allure serve ./allure-results/ --port 9000`

对于持续集成时不断更新并保留历史数据的报告应在CI中使用Allure CI插件生成

## 备注
- ~~项目考虑多环境的测试文件读取区分，默认使用beta测试环境数据执行测试~~
- 默认一个项目对应一个baseURI
- ~~考虑多项目共存一个仓库，以该层级（项目-模块-接口）进行测试数据与测试配置管理~~
- ~~项目配置文件配置层级的约定由外到内：Project Common Config->Env Common Config->Env Config，不可随意添加配置层级，可造成关键信息读取失败~~

