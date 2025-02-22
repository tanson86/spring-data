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
* orpahn removal is false by default which means the foriegn key would be set tot null, when this is true it removes this record from db.
* cascade delete will happen when parent is deleted, orphan removal is when child is removed from list and this makes parent id as null in db.
* Derive query - generate query from method name in jparepository.
* PartTree.Java and Part.java shows the different keywords one can use with derived query.
* FetchType = EAGER will work only in OneToOne relations, in OneToMany it still has the N+! isssue, We can use JOIN FETCH/BatchSize/EntityGraph to do an eager fetch.
* @Query alsways expects a Select so to pass other queries like DELETE/INSERT we should use another annonttion @Modifying. We can pass 2 params like flushAutomatically/clearAutomtically.
* Native query no cahcing, has to be changed when db changes, used for complex mapping.
* @SqlResultSetMapping and @NamedNativeQuery can be used to map sub set column selection in native queries.
* We use EntityManager to create DynamicNativeQuery, we use StringBuilder to construct the query and have question marks for place holders, we add variables to a list and later manually process the list to populate place holders before running the query.
* 

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
* HttpEntity represents an HTTP request or response consists of headers and body.
* ResponseEntity extends HttpEntity but also adds a Http status code.
* ResponseBody indicates that return value of method on which it is used is bound to the response body (Mean the return value of method is treated as Http response body)

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
  System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
  System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
* Isolation tells how the changes done by one transaction are visible to others, default isolation level will depend on the underlying db selected, mostly relational dbs use read_committed.
    Read uncommited - fetchs uncommited data (no read/write lock)
    Read committed - fetches only commited data (read lock acquired and released immediately after read or write lock acquired and held till end of transaction)
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

Bean Scopes
------------
* singleton beans are default or annotated with @Scope("singleton"), if two controllers refer to this object both the classes will have same instnace of the bean, this can be verified by printing hashcode in postConstruct. singleton beans are eagerly constructed.
* prototype beans are lazily initialised, each controller will have a differnt instance of this bean.
* request beans are tied to every request and lazily initialised.
* session beans are tied to a session and gets removed when session ends.

maven lifecycle
---------------
* validate->compile->test->package->verify->install->deploy (phases)
* we can add multiple goals to each phase for example in validate we can add pmd goal which will check for dead code/unreachable code.

JWT
----
* jwt is a token returned by authorisation server, which will further be used to interact with resource server. jwt tokens are stateless and hence used in microserves as the the token itself contains all relevant information.
* session ids are statewful and details created an entry in db(ttl,date,id,roles) and returned only the id which was further used to access resource server. on every request the server has to hit db and verify validtiy of the session.
* RSA - asymmetric and HMAC - symmetric key algo
* jwt = header(type=JWT|cryptography algo).payload(claims=issuer|subject|audience|expirtytime|id).signature(base64 encoded(header|payload|signature))
* jwt early invalidation is hard so the best option is to create a blacklisted table and add early invalidated tokens here.
* jwt/jsw is only encoded(base 64), jwe is encrypted and hence more secure.

OAUTH-2.0
---------
* Its a authentication framework and enables thrid party to get access user data.
* Actors involved Resource owner(user logging in), CLient(website requesting login), Authorisation Server (Server validating credentials), Resource hosting server(holds user data like name,age etc)
* First the client should be registered with authorisation server which will give client a id.
* To get auth code, client makes a GET call saying {response_type:code,client_id:clientId,callBack_uri:callbackuri,scope:scopes,state:random string (used to prevent csrf attack)}
* to get token, client makes a POST call with {grant_type=authorisation_code, code=auht code recieved above, redirect_uri:xyz, client_code:clientCode, client_secret:client secret recieved during registrations.}
* to get new token after expiry we make the same post call above but change grant_type=refresh_token, token: refresh token got in above calls response.

Spring jpa
-----------
* Add spring-starter-jdbc and corresponding db(h2,mysql) dependency to pom.
* Autowire JdbcTemplate and start using it, @Repository converts SQLExpcetion to exception with meaningful message, dont have to write PreparedStatement or handle connection.
* (spring.datasource.username) datasource object helps with connection pooling and database connections. hikari is default datasource and provides hikari cp for connection pooling.
* In jdbc we use sql to interact with db but with orm(springboot starter jpa default is hibernate) we use java objects to interact with db
* If we save a record and try to read it in same trnasaction it will read it from cache and not db.
* 1 persistance unit = 1 entity manager factory -> entitymamanger (session)
* When we are using multiple dbs(h2/mysql) in a spring application we need to define which all entities go to h2 and which go to mysql in configuration. also transaction type has to be JTA(one trnasaction management for 2 dbs) and not resource_local(one transaction per db).
* resource_local = JpaTransactionManager.java(transaction limited to one db), jta = JtaTransactionManager.java (single trnasaction for multiple db)
* jparepository provides @transaction by default/pagination or sort implementations/insertAll,deleteAll../dont have to worry about managing entitymamanger... these things are not provided by @PersistanceContext EntityManager
* entity life cycle - transient(newly created like new User())/persistent(entityMamanger.persist()/get()/merge())/detached(entityMamanger.close())/removed (entityMamanger.remove())
* First level cache happens in persistence context, for example suppose the user gives an insert/delete/insert/get in one transaction, the first insert/delete happens in persistance context and doesnt touch the db. final save will push persistance context to db and read will fetch from persistance context and dont touch the db.
* EntityManager(PC) is limited to one transaction.
* second level cache ie ehcache acts like redis just infront of db. this cache is populated based on read/write policy. to enable 2nd level cache artifacts have to be configured in pom and appropriate properties in application.properties. entity calsses must be annotated with @Cache and also region(cache name) must be mentioned so different caching properties can be applied to different regions.
* Composite unique key/index can be defined at Entity level only. Single column unique/index can de defined at field level too.
* @Id can be defined only on single field. If you want to have composite ID then you have to use Embeddable/Embedded or IdClass/Id
* @GenerationType is used to populate id or else we will have to manually genrate an id and send it along with object.
* Use @joincolumns to use a composite foreign key.
* onetoone/manytoone default loading is eager, onetomany/manytomany default loading is lazy
* Owner side holds the foreign key column, this makes material changes in db. Inverse side holds a reference to the owner for back tracking but doesnt have any material changes in db.
* While fetching inverse side can lead to infinite recursion of child->parent->child->etc to prevent that we can use @JsonManagedReference (owning side) or @JsonBackReference (inverse side) or use @JsonIdentityInfo on both sides.

Redis
-----
* cache aside - used in read heavy application where the application tries to read from cache it its a miss then it fetches it from db.
* write through - writes to db and cache as one atomic transaction, any failure it'll be removed from both.
* write behind - write to cache and return response. cache makes an async call to save it to db.

Threads
--------
* ThreadLocal holds the data local to a thread. If we are using threadpool then the data set on a thread will be preserved if this thread is being used by another task itll see this previous data. so in thread pool it is better to call remove() after use.
* Traditional thread/Platform thread was a wrapper around OS thread. This had 2 disadvantages 1) Thread creation was slow (resolved by connection pooling) and 2) wasting a thread resource for IO blocking like network/db call.
* Virtual threads are lightweight as they are created on heap and secondly they are associated with a OS thread only in running state, during waiting waiting state jvm releases the os thread.
* Thread.ofVirtual().start(runnableTask) or Executors.newVirtualThreadPerTaskExecutor()
* Executors.newFixedThreadPool() - fixed size and threads are alive even if they are not used.
* Executors.newCachedThreadPool() - creates a thread as per need.
* Executors.newSingleThreadExecutor() - for serial executuion.
* Executors.newWorkStealingPool()(internally created a fork join pool) or we can call ForkJoinPool.commonPool()
* In workstealingpool/forkjoinpool the following happens
    - when tasks(1,2) are submitted for execution the get put in submission queue.
    - Assume there are 2 threads(T1,T2) and each thread picks up a task. Suppose task 1 is a regular task it is worked on till completion.
    - If task 2 extends RecursiveTask(returns a value) or RecursoveAction(no return value) and thread 2 picks this task it divided the task and starts working on 1 half of the task and puts the other half in a workstealing queue. When T1 is done with Task1 it checks its worksstealing queue and then submission queue and then steals work from others threads workstealingqueue.
    - Heve every thread checks for task in the following priority 1. Check its own work stealing queue 2. Check task submission queue 3. Steal work from other threads work stealing queue.
* submit(runnable - no return value) or submit(callable - return a value)
* CompletableFuture.supplyAsync(Supplier, ExecitorService) - executorService is optional and use fork join pool by default.
* CompletableFuture.thenApply() - works on result of previous stage but on same thread.
* CompletableFuture.thenApplyAsync() - works on result of previous stage but on a diff thread.
* CompletableFuture.thenCompose()/thenComposeAsync() - start another async operation after result of previous stage.
* CompletableFuture.thenAccept()/thenAcceptAsync() - End stage, takes previous stage result and doesnt return a value (Void)
* CompletableFuture.thenCombine()/thenCombineAsync() - Used to combine 2 async tasks.

Locks
-----
* syncronised locks are monitor locks, lock acquired on the object. Threads running on diff objects will run independently threads running on same object will be blocked. works well on singleton object
* Locks and semaphonres dont create lock on objects.
* Same lock object is passed to multiple objects so each object will have to wiat for this lock to be released.
* shared lock (readonly) can be acquired by multiple threads but no exclusive(write) locks allowed
* when a thread acquires exclusive lock no other threads can get a shared lock.
* stampede lock (optimistic locking) works like something @Version in hibernate. Here an actual lock is not acquired rather we check if the stamp has changed be someother thread using validate(stamp) and take actions accordingly.
* semaphore lock takes a count in constructor which tells how many thread can acquire a lock at the same time.
* while using monitor lock(synchronised) we use wait/notufy for inter thread communication where as when we use locks we use await/signal
* The above are all locked based mechanism
* The below are lock free mechanisms compare and swap(CAS) operation - AtomicInteger/AtomicReference
* CAS always check expected value and value in memory should match, if it doesnt match then it changes expected to val in memory. expected value acts like a version.
* volatile keyword tells dont read/write from local cache but directly from memory. doesnt provide thread safety but data visiblity is guranteed.

Transaction handling in distributed systems
-------------------------------------------
* Transaction - set of tasks done to keep db in a good state.
* ACID
  * Atomicity - Either the entire tx is successful or failure. no partial success or failure
  * consistency - db in consistent state before and after tx
  * isolation - one transaction shouldnt interfere with another transaction, appears to eb serial.
  * durablity - after tx is successful even a db failure shouldnt corrupt the data
* Transaction management is easy in a single db but distributed transaction across multiple dbs are hard to manage.
* 2 Phase commit(2PC) here a TransactionCoordinator locks the record and send the query to all partcipant(order and inventory db) and then follows up with Prepare, if both parties return a yes then it sends a commit message to both parties and on getting ok response transaction manager closes. if response for prepare is not ok from any one party then abort is issued to both the parties.
* log files are maintained to handle failures, so when coordinator comes back up the partcipant can ask the status.
* Failure at approve stage is easy to handle but failure at commit stage is hard to handle and hence we have 3 phase commit.
* 3PC - prepare, precommit,commit. here the commit decision is transmitted to the partcipants in 2pc the partcipants are not aware of this decision. in 2pc the coordinator only asks can we commit and they give their response.
* commit phase is blocking and pre commit is non blocking
* partcipant cannot commit or rollback rather they pass their state to the coordinator who takes the final decision based on all responses if all can be commited or not.
* partcipants can communicate with each other in 2pc/3pc.
* saga pattern is more deep(more services involved as part of transaction) and locking will give a bad user experience, saga is asynchronous and 2pc/3pc are synchronous.