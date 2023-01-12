function updateSchoolClass(id) {
    var SchoolClassFormData = {
        Id : $("h1").data('id'),
        className : $("#editSchoolClass #className").val(),
        subject : $("#editSchoolClass #subject").val()
    };

    $.ajax({
        type: "POST",
        url: "/updateSchoolClass",
        data: SchoolClassFormData,
    })
    .done(function() {
        window.location.replace(window.location.href);
    });
}

function closeAll() {
    $("#editSchoolClass").hide();
    $("#newStudent").hide();
}

function openSchoolClassEdit() {
    if($("#editSchoolClass").is(":visible")) {
        $("#editSchoolClass").toggle();
    } else {
        closeAll();
        $("#editSchoolClass").show();

        $("#editSchoolClass #className").val($("h1").data('classname'));
        $("#editSchoolClass #subject").val($("h1").data('subject'));
    }
}

function openNewStudentWindow() {
    if($("#newStudent").is(":visible")) {
        $("#newStudent").toggle();
    } else {
        closeAll();
        $("#newStudent").show();
    }
}

function createNewStudent() {
    var StudentFormData = {
        firstName : $("#newStudent #firstName").val(),
        lastName : $("#newStudent #lastName").val()

    }

    $.ajax({
        type : "POST",
        url : window.location.href + "/newstudent",
        data : StudentFormData
    })
    .done(function() {
            location.reload();
    });
}

function showHelperContent() {
    $("#helpercontent").toggle();
}

function openGradeTest(gradeTestId) {
    window.location.replace(window.location.href + '/' + gradeTestId);
}

function goBackLevels(slashesToRemove) {

    var url = window.location.href;
    for(let i = 0; i < slashesToRemove; i++){
        var slashPos = url.lastIndexOf("/");
        url = url.substring(0,slashPos);
    }
    window.location.replace(url);
}

function showWeightings() {
    window.location.replace(window.location.href + '/weightings');
}

//NICHT NOTWENDIG?!
function calcAllAverages() {

    //check ob alle gewichtungen ausgeüllt sind
    //alle weightings holen
    var weightingspan = $("span#writtenweightingsdisplay");

    //zahlen extrahieren
    var weightingsraw = weightingspan.text().replace("(","").replace(")","").trim().split(":");
    var weightings = new Array();
    for (var i=0; i<weightingsraw.length; i++) {
        weightings.push(weightingsraw[i].split('\n').join('').trim());
    }

    //wenn es x contains
    if(!weightings.includes("x")) {

        //alle noten eines schülers holen
//        console.log($("tbody > tr").first());
        var grades = $("tbody > tr .grade").slice(4,8);
        grades.each(function() {
            console.log($(this).text());
        })
//        for(var grade of grades) {
//            console.log(grade.text());
//        }

    } else {
        console.log("nicht alle Gewichtungen festgelegt!");
    }
}