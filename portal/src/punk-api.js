document.addEventListener("DOMContentLoaded", function () {
  let currentPage = 1;
  const perPage = 25;
  let url = `http://localhost:8080/punk/beerPerPage?page=`;
  
  function fetchData(page) {
    const token = document.cookie.replace(/(?:(?:^|.*;\s*)token\s*=\s*([^;]*).*$)|^.*$/, "$1");
    fetch((url+page),{
      headers: {
        "Authorization": "Bearer "+token
      }
    })
      .then((response) => response.json())
      .then((data) => {
        const beerContainer = document.getElementById("beerContainer");
        beerContainer.innerHTML = "";

        data.forEach((beer) => {
          const beerDiv = document.createElement("div");
          beerDiv.classList.add("beer-container");

          const beerImage = document.createElement("img");
          beerImage.src = beer.image_url;
          beerImage.classList.add("beer-image");

          const beerDetails = document.createElement("div");
          beerDetails.classList.add("beer-details");

          const beerName = document.createElement("h2");
          beerName.classList.add("beer-name");
          beerName.textContent = beer.name;

          const beerTagline = document.createElement("p");
          beerTagline.classList.add("beer-tagline");
          beerTagline.textContent = beer.tagline;

          const beerDescription = document.createElement("p");
          beerDescription.classList.add("beer-description");
          beerDescription.textContent = beer.description;

          beerDetails.appendChild(beerName);
          beerDetails.appendChild(beerTagline);
          beerDetails.appendChild(beerDescription);

          beerDiv.appendChild(beerImage);
          beerDiv.appendChild(beerDetails);

          beerContainer.appendChild(beerDiv);
        });

        const previousPageButton =
          document.getElementById("previousPageButton");
        const nextPageButton = document.getElementById("nextPageButton");

        if (currentPage > 1) {
          previousPageButton.style.display = "inline-block";
        } else {
          previousPageButton.style.display = "none";
        }

        if (data.length === perPage) {
          nextPageButton.style.display = "inline-block";
        } else {
          nextPageButton.style.display = "none";
        }
      })
      .catch((error) => {
        alert("Erro ao buscar dados da API:", error);
      });
  }

  const nextPageButton = document.getElementById("nextPageButton");
  nextPageButton.addEventListener("click", function () {
    currentPage++;
    scrollToTop();
    fetchData(currentPage);
  });

  const previousPageButton = document.getElementById("previousPageButton");
  previousPageButton.addEventListener("click", function () {
    if (currentPage > 1) {
      currentPage--;
      scrollToTop();
      fetchData(currentPage);
    }
  });

  const searchButton = document.getElementById("searchButton");
  searchButton.addEventListener("click", function () {
    fetchData(currentPage);
  });

  const fetchDataButton = document.getElementById("fetchDataButton");
  fetchDataButton.addEventListener("click", function () {
    currentPage = 1;
    fetchData(currentPage);
  });

  function scrollToTop() {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  }

  


});
