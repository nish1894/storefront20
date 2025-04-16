console.log("✅ updateCart script loaded");

// Initialize on page load
document.addEventListener('DOMContentLoaded', function() {
    initializeCartButtons();
});

// Function to initialize cart buttons based on current cart state
async function initializeCartButtons() {
    console.log("initializeCartButtons function called");

    try {
        const response = await fetch('/api/cart', {
            method: 'GET',
            credentials: 'include'
        });
        
        
        if (response.ok) {
            console.log("Cart is being fetched");
            const cartData = await response.json();
            console.log("Fetched cart data:", cartData); // 🔍 Debug print

            
            // For each product on the page, update its button state
            document.querySelectorAll('[data-product-id]').forEach(productElement => {
                const itemId = productElement.getAttribute('data-product-id');
                const cartItem = cartData.items.find(item => item.id.toString() === itemId);
                
                updateButtonDisplay(itemId, cartItem ? cartItem.quantity : 0);
            });
        }
    } catch (error) {
        console.error("Error initializing cart buttons:", error);
    }
}

// Function to update cart
async function updateCart(itemId, action) {
    console.log("updateCart called with:", itemId, action);

    try {
        const response = await fetch('/api/cart/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include', // ✅ Enables session persistence
            body: JSON.stringify({ itemId, action })
        });

        if (response.ok) {
            const data = await response.json();
            updateButtonDisplay(itemId, data.quantity);
            updateCartCounter(data.totalItems);
        } else {
            console.error("Error updating cart:", await response.text());
        }
    } catch (error) {
        console.error("Error in updateCart:", error);
    }
}

// Function to update the button display based on quantity
function updateButtonDisplay(itemId, quantity) {
    const productItem = document.querySelector(`[data-product-id="${itemId}"]`);
    
    if (!productItem) {
        console.warn(`Product item ${itemId} not found`);
        return;
    }
    
    const addButton = productItem.querySelector('.add-to-cart-button');
    const quantityControls = productItem.querySelector('.quantity-controls');
    const quantityInput = quantityControls ? quantityControls.querySelector('input') : null;
    
    if (quantity > 0) {
        // Item is in cart - show quantity controls, hide add button
        if (addButton) addButton.style.display = 'none';
        if (quantityControls) {
            quantityControls.style.display = 'flex';
            if (quantityInput) quantityInput.value = quantity;
        }
    } else {
        // Item not in cart - show add button, hide quantity controls
        if (addButton) addButton.style.display = 'inline-flex';
        if (quantityControls) quantityControls.style.display = 'none';
    }
}

// Function to update cart counter in the header/navbar
function updateCartCounter(totalItems) {
    const cartCounter = document.getElementById('cart-counter');
    if (cartCounter) {
        cartCounter.textContent = totalItems;
        
        // If counter was hidden and now has items, make it visible
        if (totalItems > 0) {
            cartCounter.classList.remove('hidden');
        } else {
            cartCounter.classList.add('hidden');
        }
    }
}
