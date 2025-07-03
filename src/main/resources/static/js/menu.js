const menuLateral = document.querySelector('nav.menu-lateral');
const btnExpandir = document.querySelector('.btn-expandir > i');

btnExpandir.addEventListener('click', () => {
    menuLateral.classList.toggle('expandido');
});