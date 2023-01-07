::Create 2 SchoolClasses
::
curl -X POST -d "className=Klasse+5r" -d "subject=Deutsch" http://localhost:8080/
curl -X POST -d "lassName=Klasse+7a" -d "subject=Biologie" http://localhost:8080/


::Create a shitton of Students
::
curl -X POST -d "firstName=Christian" -d "lastName=Maier" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Manuel" -d "lastName=Brusche" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Anna" -d "lastName=Müller" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Stefan" -d "lastName=Bauer" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Michelle" -d "lastName=Tränk" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=David" -d "lastName=Agrat" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Xenia" -d "lastName=Sterk" http://localhost:8080/schoolclasses/1/newstudent