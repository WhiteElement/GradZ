function updateSchoolClass(id) {
    var SchoolClassFormData = {
        Id : id,
        className : $(editSelector(id) + ' #editClassName' + id).val(),
        subject : $(editSelector(id) + ' #editSubject' + id).val()
    };

    $.ajax({
        type: "POST",
        url: "/updateSchoolClass",
        data: SchoolClassFormData,
    })
    .done(function() {
        alert("klappt");
        window.location.replace(window.location.href);
    });
}