//index

async function updateSchoolClass(inputelem) {
    let url = window.location.href;
    var SchoolClassFormData = {
        id : parseInt(inputelem.parentElement.dataset.id),
        className : inputelem.parentElement.querySelector(".className").value,
        subject : inputelem.parentElement.querySelector(".classSubject").value
    };

    const response = await fetch(url +"updateSchoolClass", {
        method : "POST",
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(SchoolClassFormData)
    });

    if(!response.ok) throw response;
    if(response.ok) {
        window.location.replace(url);
    }
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

function toggle(elem) {
    if(elem.style.display == "block") {
        elem.style.display = 'none';
    } else {
        elem.style.display = 'block';
    }
}
