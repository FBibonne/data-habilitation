# Data access control web plugin

This plugin is designed to check accesses over datas on a web application (Rest or not). 
Many solutions exist in order to check accesses over functionnalities and it is not the aim of this
plugin. This plugin can be easily plugged to any web application (which executes in a servlet 
container) and checks that users access pieces of data they are allowed.

## How does it work

### Features

### Architectures and technics

The plugins is plugged as a [servlet filter](https://tomcat.apache.org/tomcat-9.0-doc/servletapi/javax/servlet/Filter.html)
to the web application. The filter is detected by the servlet container (e.g tomcat) with
`@WebFilter` annotation.

---
>So if you use en embeded servlet container, such as tomcat in a spring boot web app, you need 
>to enable `javax.servlet.annotation.*` detection. For example with a spring boot web app, you
>must add the `@ServletComponentScan` annotation
---

## Next

- [ ] Add feature : if user is not authentified, let pass the request
- [ ] Loading :
  - [ ] late loading if tomcat instantiates the plugin before the master app
  - [ ] Search configuration order :
    1. In master application spring context 
    2. In system properties
    3. default configuration 
- [ ] default implementations
- [ ] externalise properties
- [ ] doc (javadoc and "user manual") and test core classes
- [ ] externalise plugin as a maven plugin (packages, project, pom, master app conf requirements)
