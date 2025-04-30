document.addEventListener("DOMContentLoaded", function () {
  const searchInput = document.getElementById("searchInput");
  const sortSelect = document.getElementById("sortSelect");
  let timeout = null;

  // Search with debounce
  searchInput?.addEventListener("input", function (e) {
    clearTimeout(timeout);
    timeout = setTimeout(() => {
      const searchTerm = e.target.value;
      const [sortBy, direction] = sortSelect.value.split(",");
      fetchProducts(searchTerm, sortBy, direction);
    }, 500);
  });

  // Sort
  sortSelect?.addEventListener("change", function (e) {
    const searchTerm = searchInput.value;
    const [sortBy, direction] = e.target.value.split(",");
    fetchProducts(searchTerm, sortBy, direction);
  });

  async function fetchProducts(title = "", sortBy = "id", direction = "asc") {
    try {
      const url = new URL("/store/home/search-view", window.location.origin);
      url.searchParams.append("title", title);
      url.searchParams.append("sortBy", sortBy);
      url.searchParams.append("direction", direction);
      url.searchParams.append("page", "0");
      url.searchParams.append("size", "20");

      const response = await fetch(url);
      const html = await response.text();

      // Find the products grid and update it
      const productsGrid = document.getElementById("products-grid");
      if (productsGrid) {
        // Create a temporary container
        const temp = document.createElement("div");
        temp.innerHTML = html;

        // Find the new grid content
        const newContent = temp.querySelector("#products-grid");
        if (newContent) {
          productsGrid.innerHTML = newContent.innerHTML;
        } else {
          console.error("Could not find products grid in response");
        }
      }

      // Update URL without page reload
      const newUrl = `/store/home?${url.searchParams.toString()}`;
      window.history.pushState({}, "", newUrl);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  }
});
