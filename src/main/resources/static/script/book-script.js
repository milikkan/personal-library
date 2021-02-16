// author selection dropdown
const authorsSelect = document.getElementById("bookAuthorsSelect");
// publisher selection dropdown
const publisherSelect = document.getElementById("bookPublisherSelect");
// buttons to add selected author and publisher to the text inputs
const addAuthorFromSelectBtn = document.getElementById("addAuthorFromSelect");
const addPublisherFromSelectBtn = document.getElementById("addPublisherFromSelect");
// division to hold the list of authors for the current book
const newAuthorList = document.getElementById("newAuthorList");
// add author button of the last author row
const addAuthorRowBtn = findLastElement("author-add-btn");
// remove author button of the last author row
const removeAuthorRowBtn = findLastElement("author-remove-btn");
// increment this for each uthor added, and decrement for each author removed
let rowIndex = getAuthorRowCount() - 1;

sortOptions(authorsSelect);
sortOptions(publisherSelect);
initializeAddRemoveAuthorButtons();

addAuthorFromSelectBtn.addEventListener("click", () => {
    const lastRow = findLastElement("author-row");

    const authorNameInput = findLastElement("author-name-input");
    const authorExpInput = findLastElement("author-exp-input");

    const select = getSelectedOption(authorsSelect);

    if (select.value !== "-1") {
        authorNameInput.value = select.text;
        authorExpInput.value = select.value;
    }
});

addPublisherFromSelectBtn.addEventListener("click", () => {
    const publisherNameInput = document.getElementById("addPublisherName");
    const publisherExpInput = document.getElementById("addPublisherExplanation");

    const select = getSelectedOption(publisherSelect);

    if (select.value !== "-1") {
        publisherNameInput.value = select.text;
        publisherExpInput.value = select.value;
    }
});

function addNewAuthorRow () {
    rowIndex++;
    const prevAddBtn = findLastElement("author-add-btn");
    const prevRemoveBtn = findLastElement("author-remove-btn");

    const lastRow = createNewAuthorRow(rowIndex);
    newAuthorList.appendChild(lastRow);

    const lastAddBtn = findLastElement("author-add-btn");
    lastAddBtn.addEventListener("click", addNewAuthorRow);

    const lastRemoveBtn = findLastElement("author-remove-btn");
    lastRemoveBtn.addEventListener("click", removeNewAuthorRow);

    prevAddBtn.setAttribute("disabled", "");
    prevRemoveBtn.setAttribute("disabled", "");
}

function removeNewAuthorRow() {
    const lastRow = findLastElement("author-row");
    if (rowIndex > 0) {
        const prevRow = lastRow.previousElementSibling;
        newAuthorList.removeChild(lastRow);

        prevRow.querySelector(".author-add-btn").removeAttribute("disabled");
        if (rowIndex > 1) {
            prevRow.querySelector(".author-remove-btn").removeAttribute("disabled");
        }
        rowIndex--;
    }
}

function getSelectedOption(sel) {
    var opt;
    for ( var i = 0, len = sel.options.length; i < len; i++ ) {
        opt = sel.options[i];
        if ( opt.selected === true ) {
            break;
        }
    }
    return opt;
}

function createNewAuthorRow(rowIndex) {
    // create row div
    const rowDiv = document.createElement("div");
    rowDiv.classList.add("row", "mb-3", "author-row");

    const html = `
        <div class="col">
            <input type="text" class="form-control author-name-input" name="${'authors[' + rowIndex + '].fullName'}">
        </div>
        <div class="col">
            <input type="text" class="form-control author-exp-input" name="${'authors[' + rowIndex + '].explanation'}">
        </div>
        <div class="col align-self-end">
            <button class="btn btn-primary author-add-btn" type="button">Yeni Ekle</button>
            <button class="btn btn-danger author-remove-btn" type="button">Kaldır</button>
        </div>
    `;
    rowDiv.innerHTML = html;
    return rowDiv;
}

function sortOptions(select) {
    const options = select.options;
    const defaultOption = options[0];
    let optionsArray = [];

    for (let i = 1; i < options.length; i++) {
        optionsArray[i] = options[i];
    }

    optionsArray = optionsArray.sort(function (a, b) {
        return a.innerHTML.toLowerCase().charCodeAt(0) - b.innerHTML.toLowerCase().charCodeAt(0);
    });

    for (let i = 0; i < optionsArray.length; i++) {
        options[i+1] = optionsArray[i];
    }

    options[0] = defaultOption;
    options[0].selected = true;
}

function getAuthorRowCount() {
    return newAuthorList.querySelectorAll(".author-row").length;
}

function findAllElements(className) {
    return  newAuthorList.querySelectorAll(`.${className}`);
}

function findLastElement(className) {
    const list = findAllElements(className);
    return list[list.length - 1];
}

function initializeAddRemoveAuthorButtons() {
    addAuthorRowBtn.removeAttribute("disabled");
    if (rowIndex > 0) {
        removeAuthorRowBtn.removeAttribute("disabled");
    }

    findAllElements("author-add-btn").forEach(btn => {
        btn.addEventListener("click", addNewAuthorRow);
    });

    findAllElements("author-remove-btn").forEach(btn => {
        btn.addEventListener("click", removeNewAuthorRow);
    });
}