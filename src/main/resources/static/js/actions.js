function closeAll() {
    $("#editSchoolClass").hide();
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

function cleanUpUrl(url) {
    if(url.endsWith("/")) {
        return url.substring(0,url.length-1);
    }
    return url;
}
