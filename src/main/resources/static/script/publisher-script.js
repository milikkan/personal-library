const showPublishedBooksBtn = document.querySelectorAll("a.show-books-btn");
showPublishedBooksBtn.forEach(btn => {
    btn.addEventListener("click", (e) => {
        fetchPublisherData(e.target.id);
    })
});

async function fetchPublisherData(id) {
    const response = await fetch(`http://localhost:8080/publishers/${id}/books`, {
        headers: {
            Accept: "application/json",
        },
    });
    const data = await response.json();
    const modalBookList = document.getElementById("modelBookList");
    modalBookList.innerHTML = "";
    data.forEach(bookName => {
       modalBookList.innerHTML += `<li>${bookName}</li>`;
    });
}
