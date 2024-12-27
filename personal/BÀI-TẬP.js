function loadContent(file) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", file, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("exercise-content").innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}
