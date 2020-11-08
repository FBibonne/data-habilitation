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

- [ ] Registration of filter 
  - [ ] order : this filter must be the last of the filter chain
  - [ ] spring : this filter must be loaded wether the master app is a spring application or not
    - [ ] so the runtime dependencies of the application must not contain spring web pacakges 
- [ ] Add feature : if user is not authentified, let pass the request
- [ ] Loading :
  - [ ] late loading if tomcat instantiates the plugin before the master app
  - [ ] Search configuration order :
    1. In master application spring context 
    2. In system properties
    3. default configuration 
- [ ] default implementations :
  - [ ] Get user id from request (default UserIdFinder) :
    - keycloak : `(KeycloakSecurityContext) req.getAttribute(KeycloakSecurityContext.class.getName());`
  - [ ] Default ResourcesAllowedService : default way to get Resources allowed for a given user id 
  (by properties file in classpath)
  - [ ] Default ResourcesMatcher : default algorithm to check if two resources match
- [ ] externalise properties
- [ ] doc (javadoc and "user manual") and unit test core classes
- [ ] Create docker images to test product in many architectures (spring app with embeded tomcat, 
spring app with external tomcat, non spring app)
- [ ] externalise plugin as a maven plugin (packages, project, pom, master app conf requirements)
