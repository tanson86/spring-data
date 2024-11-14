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