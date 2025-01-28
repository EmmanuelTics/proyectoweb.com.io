const hamBurger = document.querySelector(".toggle-btn");

// Alterna la clase 'expand' en el sidebar cuando se hace clic en el botón de hamburguesa
hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});

// Evita que el menú se cierre al hacer clic en los enlaces del sidebar
document.querySelectorAll('.sidebar-link').forEach(link => {
  link.addEventListener('click', (event) => {
    event.stopPropagation(); // Detiene la propagación del clic hacia el botón de toggle
  });
});
