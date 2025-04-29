console.log("✅ updateCart script loaded");

// Initialize on page load
// document.addEventListener('DOMContentLoaded', function() {
//     initializeCartButtons();
// });

// Function to initialize cart buttons based on current cart state
// async function initializeCartButtons() {
//     console.log("initializeCartButtons function called");

//     try {
//         const response = await fetch('/api/cart', {
//             method: 'GET',
//             credentials: 'include'
//         });

//         if (response.ok) {
//             console.log("Cart is being fetched");
//             const cartData = await response.json();
//             console.log("Fetched cart data:", cartData); // 🔍 Debug print

//             // For each product on the page, update its button state
//             document.querySelectorAll('[data-product-id]').forEach(productElement => {
//                 const itemId = productElement.getAttribute('data-product-id');
//                 const cartItem = cartData.items.find(item => item.id.toString() === itemId);

//                 updateButtonDisplay(itemId, cartItem ? cartItem.quantity : 0);
//             });
//         }
//     } catch (error) {
//         console.error("Error initializing cart buttons:", error);
//     }
// }

// Function to update cart
async function updateCart(itemId, action) {
  console.log("updateCart called with:", itemId, action);

  try {
    const response = await fetch("/api/cart/update", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
      body: JSON.stringify({ itemId, action }),
    });

    if (response.ok) {
      const data = await response.json();
      updateCartCounter(data.itemCount);

      // If action is remove, animate the removal
      if (action === "remove") {
        removeCartItem(itemId);
      } else {
        updateProductDisplay(itemId, data.items);
      }
    } else {
      console.error("Error updating cart:", await response.text());
    }
  } catch (error) {
    console.error("Error in updateCart:", error);
  }
}

// New function to handle cart item removal
function removeCartItem(itemId) {
  const cartItem = document.getElementById(`cart-item-${itemId}`);
  if (!cartItem) return;

  // Add fade out effect
  cartItem.style.transition = "all 0.3s ease-out";
  cartItem.style.opacity = "0";
  cartItem.style.transform = "translateX(20px)";

  // Remove the element after animation
  setTimeout(() => {
    cartItem.remove();

    // Check if cart is empty and show empty cart message if needed
    const remainingItems = document.querySelectorAll('[id^="cart-item-"]');
    if (remainingItems.length === 0) {
      showEmptyCartMessage();
    }
  }, 300);
}

// Optional: Function to show empty cart message
function showEmptyCartMessage() {
  const cartContainer = document.querySelector(".space-y-6"); // Adjust selector as needed
  if (cartContainer) {
    const emptyMessage = document.createElement("div");
    emptyMessage.className = "text-center py-8";
    emptyMessage.innerHTML = `
      <p class="text-gray-500 dark:text-gray-400 text-lg">Your cart is empty</p>
      <a href="/store/home" class="inline-flex items-center text-blue-600 hover:underline mt-4">
        Continue Shopping
        <svg class="ml-1 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8l4 4m0 0l-4 4m4-4H3"/>
        </svg>
      </a>
    `;
    cartContainer.appendChild(emptyMessage);
  }
}

// // Function to update the button display based on quantity
// function updateButtonDisplay(itemId, quantity) {
//     const productItem = document.querySelector(`[data-product-id="${itemId}"]`);

//     if (!productItem) {
//         console.warn(`Product item ${itemId} not found`);
//         return;
//     }

//     const addButton = productItem.querySelector('.add-to-cart-button');
//     const quantityControls = productItem.querySelector('.quantity-controls');
//     const quantityInput = quantityControls ? quantityControls.querySelector('input') : null;

//     if (quantity > 0) {
//         // Item is in cart - show quantity controls, hide add button
//         if (addButton) addButton.style.display = 'none';
//         if (quantityControls) {
//             quantityControls.style.display = 'flex';
//             if (quantityInput) quantityInput.value = quantity;
//         }
//     } else {
//         // Item not in cart - show add button, hide quantity controls
//         if (addButton) addButton.style.display = 'inline-flex';
//         if (quantityControls) quantityControls.style.display = 'none';
//     }
// }

// Function to update product display
function updateProductDisplay(itemId, cartItems) {
  const productItem = document.querySelector(`[data-product-id="${itemId}"]`);
  if (!productItem) return;

  const addButton = productItem.querySelector(".add-to-cart-button");
  const quantityControls = productItem.querySelector(".quantity-controls");
  const quantitySpan = quantityControls.querySelector("span");

  const cartItem = cartItems.find((item) => item.id === itemId);

  if (cartItem && cartItem.quantity > 0) {
    // Item is in cart
    addButton.style.display = "none";
    quantityControls.style.display = "flex";
    quantitySpan.textContent = cartItem.quantity;
  } else {
    // Item not in cart
    addButton.style.display = "inline-flex";
    quantityControls.style.display = "none";
  }
}

// Function to update cart counter in the navbar
function updateCartCounter(count) {
  const cartCounter = document.getElementById("cart-counter");
  if (cartCounter) {
    cartCounter.textContent = count;

    // Optionally hide/show the counter based on count
    if (count > 0) {
      cartCounter.style.display = "inline-flex";
    } else {
      cartCounter.style.display = "none";
    }
  }
}