// Lógica para expandir o menu lateral
const menuLateral = document.querySelector('nav.menu-lateral');
const btnExpandir = document.querySelector('.btn-expandir > i');

btnExpandir.addEventListener('click', () => {
    menuLateral.classList.toggle('expandido');

    const main = document.getElementById('conteudo-principal');
    if (menuLateral.classList.contains('expandido')) {
        main.style.marginLeft = '230px';
    } else {
        main.style.marginLeft = '80px';
    }
});

// Função para trocar o conteúdo do <main> dinamicamente
// function carregarConteudo(secao) {
//     const main = document.getElementById('conteudo-principal');
//
//     switch (secao) {
//         case 'posts':
//             main.innerHTML = `
//         <h1>Todos os Posts</h1>
//         <p>Aqui estão todos os posts cadastrados no sistema.</p>
//       `;
//             break;
//         case 'cupons':
//             main.innerHTML = `
//         <h1>Criar Cupons</h1>
//         <p>Use esta área para criar cupons promocionais.</p>
//       `;
//             break;
//         case 'categorias':
//             main.innerHTML = `
//         <h1>Categorias</h1>
//         <p>Gerencie as categorias dos cupons e produtos.</p>
//       `;
//             break;
//         case 'lojas':
//             main.innerHTML = `
//         <h1>Lojas</h1>
//         <p>Administre as lojas cadastradas no sistema.</p>
//       `;
//             break;
//         case 'links':
//             main.innerHTML = `
//         <h1>Pretty Links</h1>
//         <p>Configure links personalizados e redirecionamentos.</p>
//       `;
//             break;
//         default:
//             main.innerHTML = `
//         <h1>Bem-vindo</h1>
//         <p>Escolha uma opção no menu lateral.</p>
//       `;
//             break;
//     }
// }
