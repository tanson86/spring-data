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