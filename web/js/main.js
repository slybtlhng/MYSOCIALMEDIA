const inputs = document.querySelectorAll(".input");


function addcl() {
    let parent = this.parentNode.parentNode;
    parent.classList.add("focus");
}

function remcl() {
    let parent = this.parentNode.parentNode;
    if (this.value == "") {
        parent.classList.remove("focus");
    }
}


inputs.forEach(input => {
    input.addEventListener("focus", addcl);
    input.addEventListener("blur", remcl);
});

$(document).ready(function () {
    $('input[type="file"]').change(function (e) {
        let fileLabel = document.getElementById("txtFileName");
        var fileName = e.target.files[0].name;
        fileLabel.innerHTML = fileName;
    });
});
