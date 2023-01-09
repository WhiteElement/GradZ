::Create 2 SchoolClasses
::
CHCP 1252
curl -X POST -d "className=Klasse+5r" -d "subject=Deutsch" http://localhost:8080/
curl -X POST -d "className=Klasse+7a" -d "subject=Biologie" http://localhost:8080/


::Create a shitton of Students
::
curl -X POST -d "firstName=Christian" -d "lastName=Maier" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Manuel" -d "lastName=Brusche" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Anna" -d "lastName=Müller" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Stefan" -d "lastName=Bauer" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Michelle" -d "lastName=Tränk" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=David" -d "lastName=Agrat" http://localhost:8080/schoolclasses/1/newstudent
curl -X POST -d "firstName=Xenia" -d "lastName=Sterk" http://localhost:8080/schoolclasses/1/newstudent

::Create a shitton of Students Klasse 2
::
curl -X POST -d "firstName=Christian2" -d "lastName=Maier2" http://localhost:8080/schoolclasses/2/newstudent
curl -X POST -d "firstName=Manuel2" -d "lastName=Brusche2" http://localhost:8080/schoolclasses/2/newstudent
curl -X POST -d "firstName=Anna2" -d "lastName=Müller2" http://localhost:8080/schoolclasses/2/newstudent
curl -X POST -d "firstName=Stefan2" -d "lastName=Bauer2" http://localhost:8080/schoolclasses/2/newstudent
curl -X POST -d "firstName=Michelle2" -d "lastName=Tränk2" http://localhost:8080/schoolclasses/2/newstudent
curl -X POST -d "firstName=David2" -d "lastName=Agrat2" http://localhost:8080/schoolclasses/2/newstudent
curl -X POST -d "firstName=Xenia2" -d "lastName=Sterk2" http://localhost:8080/schoolclasses/2/newstudent





::Create GradeTests
::
curl -X POST -d "gradeType=WRITTEN" -d "testName=KA+#1" -d "testDescription=erste+Klassenarbeit" http://localhost:8080/schoolclasses/1/newgradetest
curl -X POST -d "gradeType=WRITTEN" -d "testName=KA+#2" -d "testDescription=zweite+Klassenarbeit" http://localhost:8080/schoolclasses/1/newgradetest
curl -X POST -d "gradeType=ORAL" -d "testName=24.10" -d "testDescription=Abfrage+in+Klasse" http://localhost:8080/schoolclasses/1/newgradetest


::Create Grades
::
curl -X POST -d "Grade=2" http://localhost:8080/schoolclasses/1/1/1
curl -X POST -d "Grade=2.5" http://localhost:8080/schoolclasses/1/1/2
curl -X POST -d "Grade=1" http://localhost:8080/schoolclasses/1/1/3
curl -X POST -d "Grade=4.3" http://localhost:8080/schoolclasses/1/1/4
curl -X POST -d "Grade=3" http://localhost:8080/schoolclasses/1/1/5