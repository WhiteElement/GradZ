//index

async function updateSchoolClass(inputelem) {
    let url = window.location.href;
    var SchoolClassFormData = {
        id : parseInt(inputelem.parentElement.dataset.id),
        className : inputelem.parentElement.querySelector(".className").value,
        subject : inputelem.parentElement.querySelector(".classSubject").value
    };

    const response = await fetch("/updateSchoolClass", {
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
}

function openSchoolClassEdit() {
    if($("#helpercontent").is(":visible")) {
        $("#helpercontent").toggle();
    }

    if($("#editSchoolClass").is(":visible")) {
        $("#editSchoolClass").toggle();
    } else {
        closeAll();
        $("#editSchoolClass").show();

        $("#editSchoolClass #className").val($("h1").data('classname'));
        $("#editSchoolClass #classSubject").val($("h1").data('subject'));
    }

}

function openNewStudentWindow() {
    const elems = [document.querySelector("td #firstName"), document.querySelector("td #lastName")] ;

    elems.forEach(elem => {
        if(elem.style.display == "table-cell") {
            elem.style.display = 'none';
        } else {
            elem.style.display = 'table-cell';
        }
        elem.value = "";
    });

    toggle(document.querySelector("td #createbutton"));
}

function createNewStudent() {
    var StudentFormData = {
        firstName : $("td #firstName").val(),
        lastName : $("td #lastName").val()
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
    if($("#editSchoolClass").is(":visible")) {
        $("#editSchoolClass").toggle();
    }
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

function toggle(elem) {
    if(elem.style.display == "block") {
        elem.style.display = 'none';
    } else {
        elem.style.display = 'block';
    }
}

//allgemein
function cleanUpUrl(url) {
console.log(url);
    if(url.endsWith("/")) {
        console.log(url.substring(0,url.length-1));
        return url.substring(0,url.length-1);
    }
    return url;
}
