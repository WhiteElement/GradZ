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
