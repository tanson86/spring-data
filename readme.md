This project demonstrates important hibernate annotation in spring data
-----------------------------------------------------------------------
* @PrimaryKeyJoinColumn and @MapsId is used in conjunction so the same primary key is used in both tables.
* @joincolumn is the owning side of the entity in bi directional mapping and 
the other entity should declare the mappedBy property. mappedBy property 
usually refers to attribute name mentioned in owning entity. when you define the joincolumn
an extra table is not created rather an extra column is created in owning side 
which acts as foriegn key.
* in a one to many or one to one relation if joinkey is not defined then it wil create
a join table.

Understanding of exception resolver
-----------------------------------
* When dispatcher servelet generates an error or gets error returned from controller it is passed onto a composite ie <b>HandlerExceptionResolverComposite</b>
which inturn delegates this error to 3 resolver to resolve it.
* ExceptionHandlerExceptionResolved - handles error annotated in @ExceptionHandler(at each controller) or @ControllerAdvice(one at global level for all controllers)
* ExceptionStatusExceptionResolver - handles error annotated with @ResponseStatus.
* DefaultHandlerExcptionResolver - handles all other exception defined in this class for example resource not found for bad url.
* Once a resolver handles the error it is not passed onto another resolver.
* If none of the resolvers resolves the error then a basic resopnse entity is created by DefaultErrorAttributes

Understanding Response from Controller
--------------------------------------
* @RestController annotation internally annotates all methods in the controller class with @ResponseBody 
and this is not the case with @Controller where if @ResponseBody is not explicitly mentioned 
it treats it as a string and looks for an appropriate view to render(here the string will be treated 
 like a file name to render).
* Difference between MOVED_PERMANENTLY and PERMANENT_REDIRECT is that MOVED_PERMANENTLY allows you to chnage http verb from GET to POST etc
where as the PERMANENT_REDIRECT will not allow replacing the original HTTP verb with anything else.

Understanding INTERCEPTORS
--------------------------
* Implement HandlerInterceptor which has 3 methods. Dispatcher servlet first call the preHandle api then calls the contr0ller endpoint, if prehandle returns false then next interceptor/controller will not get called.
and then call the post handle api (if there is no exception) and the calls the aftercompletion api(called if there is exception or not, something like finally in java)
* This interceptor must be registered by implementing WebMvcConfigurer. Here we say what endpoints should be intercepted.
* We can even use aop to create custom intercpetors.
* Interceptor is specific to spring framework and jumps into action between dispatcher servlet and conroller where as a Filter
is a generic http concept where requests are read/amended even before reaching the dispatcher servlet. Spring boot security is an example for filter.
* sequence of filters/interceptors defined in file will be respected. Filter is  global, interceptor can be applied to specific controllers.

Understanding transactions
--------------------------
* whenever we add data-jpa in pom or gradle file we need to mention a db driver like mysql/h2.
* Transactions use AOP concept to commit and rollback transactions.
* We can use a <b>TransactionTemplate</b> to handle tramsaction manually/via code
* Datasource bean is used to create a PlatformTransactionManager bean which is in turn used to create TransactionTemplate or JpaTransactionManager or HibernateTransactionManager
* We can print below 2 to check transaction status/name
    sop(TransactionSyncronizationManager.isActualTrnasactionAlive())
    sop(TransactionSyncronizationManager.getCurrentTransactionName())
* Isolation tells how the changes done by one transaction are visible to others, default isolation level will depend on the underlying db selected, mostly relational dbs use read_committed.
    Read uncommited - fetchs uncommited data (no read/write lock)
    Read committed - fetches only commited data (read lock acquired and released immediately or write lock acquired and held till end of transaction)
    Non repeatable read - running fetch multiple times yields diff result of same record as someother transaction might have commited the data. (read/write lock held till end of transaction)
    phantom read - running fetch multiple times will yield different number of records(locks same as non repeatable read but applies a range lock) age>1 and age<5 (here range would be 2,3,4 and all these ids would be locked)

Bean and its lifecycle
-----------------------
* @component works only when there is a default constructor,@Bean will have to be used if there is no default constructor and only has user defined constructor.
* @ComponentScan will be able to scan a package and sub packages
* Singleton scoped beans are eagerly created where as prototype or component anotated with @lazy are lazily initialised.
* WebApplicationContext is ioc/spring contianer and can be seen in spring boot startup console.
* Spring uses reflections to scan each property for autowired and create them after actual object has been created.
* Constructor injection is the most preferred as it supposrts immutablelity, latest spring allows constructor injection without using @Autowired if there is only one constructor. If there is more than one constructor then @Autowired is mandatory and spring only initialiese/uses that constructor.
* Use @Primary or @Qualifier if you have 2 classes implementing an interface.
* Use refactoring/@Lazy to resolve a circular dependency.