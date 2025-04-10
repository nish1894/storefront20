console.log("✅ updateCart script loaded");

async function updateCart(itemId, action) {
    console.log("updateCart called with:", itemId, action);

 
    const response = await fetch('/api/cart/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include', // ✅ THIS is the fix — enables session persistence
        body: JSON.stringify({ itemId, action })
    });

    if (response.ok) {
        const data = await response.json();
        const qtyElem = document.querySelector(`#qty-${itemId}`);
        const wrapper = document.querySelector(`#cart-wrapper-${itemId}`);

        if (data.quantity > 0) {
            qtyElem.innerText = data.quantity;
        } else {
            wrapper.innerHTML = `
                <button onclick="updateCart(${itemId}, 'add')" class="btn">Add to Cart</button>
            `;
        }
    }
}
