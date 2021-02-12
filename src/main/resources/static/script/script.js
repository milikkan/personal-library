const authorsSelect = document.getElementById("bookAuthorsSelect");
const publisherSelect = document.getElementById("bookPublisherSelect");
const addNewAuthorBtn = document.querySelector(".active-add-btn");
const removeNewAuthorBtn = document.querySelector(".active-remove-btn");

const newAuthorList = document.getElementById("newAuthorList");
const addAuthorFromSelectBtn = document.getElementById("addAuthorFromSelect");

const addPublisherFromSelectBtn = document.getElementById("addPublisherFromSelect");

let rowIndex = 0;

const addNewAuthorRow = (e) => {
    rowIndex++;
    const prevRow = document.getElementById("lastRow");
    prevRow.removeAttribute("id");
    const lastRow = createNewAuthorRow(rowIndex);
    newAuthorList.appendChild(lastRow);

    e.target.classList.add("disabled");
    const addBtnList = document.querySelectorAll(".active-add-btn");
    addBtnList[addBtnList.length - 1].addEventListener("click", addNewAuthorRow);

    const removeBtnList = document.querySelectorAll(".active-remove-btn");
    removeBtnList[removeBtnList.length - 1].addEventListener("click", removeNewAuthorRow);
    removeBtnList[removeBtnList.length - 1].classList.remove("disabled");
    removeBtnList[removeBtnList.length - 2].classList.add("disabled");
};

const removeNewAuthorRow = () => {
    const lastRow = document.getElementById("lastRow");
    if (rowIndex > 0) {
        const prevRow = lastRow.previousElementSibling;
        newAuthorList.removeChild(lastRow);
        prevRow.id = "lastRow";
        const addBtnList = document.querySelectorAll(".active-add-btn");
        addBtnList[addBtnList.length - 1].classList.remove("disabled");
        if (rowIndex > 1) {
            const removeBtnList = document.querySelectorAll(".active-remove-btn");
            removeBtnList[removeBtnList.length - 1].classList.remove("disabled");
        }
        rowIndex--;
    }
};

const getSelectedOption = (sel) => {
    var opt;
    for ( var i = 0, len = sel.options.length; i < len; i++ ) {
        opt = sel.options[i];
        if ( opt.selected === true ) {
            break;
        }
    }
    return opt;
}

const createNewAuthorRow = function (rowIndex) {
    // create row div
    const rowDiv = document.createElement("div");
    rowDiv.classList.add("row", "mb-3");
    rowDiv.id = "lastRow";

    const html = `
        <div class="col">
            <input type="text" class="form-control" name="${'authors[' + rowIndex + '].fullName'}">
        </div>
        <div class="col">
            <input type="text" class="form-control" name="${'authors[' + rowIndex + '].explanation'}">
        </div>
        <div class="col align-self-end">
            <button class="btn btn-primary active-add-btn" type="button">Yeni Ekle</button>
            <button class="btn btn-danger active-remove-btn" type="button">Kaldır</button>
        </div>
    `;
    rowDiv.innerHTML = html;
    return rowDiv;
};

addAuthorFromSelectBtn.addEventListener("click", () => {
   // find the last row
    const lastRow = document.getElementById("lastRow");
    const inputs = lastRow.querySelectorAll(".form-control");
    const authorNameInput = inputs[0];
    const authorExpInput = inputs[1];

    const select = getSelectedOption(authorsSelect);

    // set values from authors object
    authorNameInput.value= select.text;
    authorExpInput.value = select.value;
});

addPublisherFromSelectBtn.addEventListener("click", () => {
    const publisherNameInput = document.getElementById("addPublisherName");
    const publisherExpInput = document.getElementById("addPublisherExplanation");

    const select = getSelectedOption(publisherSelect);

    // set values from authors object
    publisherNameInput.value= select.text;
    publisherExpInput.value = select.value;
});

addNewAuthorBtn.addEventListener("click", addNewAuthorRow);
removeNewAuthorBtn.addEventListener("click", removeNewAuthorRow);