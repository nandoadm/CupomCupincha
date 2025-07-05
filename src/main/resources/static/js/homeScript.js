document.addEventListener("DOMContentLoaded", () => {
  const dateControl = document.getElementById("inputDate");
  if (dateControl) {
    const data = new Date();
    const dia = String(data.getDate()).padStart(2, '0');
    const mes = String(data.getMonth() + 1).padStart(2, '0');
    const ano = data.getFullYear();
    dateControl.innerHTML = `${dia}/${mes}/${ano}`;
  } else {
    console.warn("Elemento com ID 'inputDate' não encontrado.");
  }
});

function mostrarDiv() {
  const elemento = document.getElementById("operador");
  elemento.style.display = elemento.style.display === "none" ? "block" : "none";
}

function mostrarCodigo(codigo) {
  const input = document.getElementById("codigo");
  if (input) input.value = codigo;
}

async function copiarCupom() {
  const input = document.getElementById("codigo");
  if (!input || !input.value) return;

  try {
    await navigator.clipboard.writeText(input.value);
    mostrarMensagem("Cupom copiado com sucesso!");
  } catch (err) {
    console.error("Erro ao copiar o cupom:", err);
  }
}

function mostrarMensagem(texto) {
  const nota = document.querySelector(".popup-note");
  if (!nota) return;
  const original = nota.innerHTML;
  nota.innerHTML = `<i class="fas fa-check-circle"></i> ${texto}`;
  setTimeout(() => nota.innerHTML = original, 2000);
}

let url = "#";

function receberLink(link) {
  url = link;
}

function abrirNovaGuia() {
  if (url && url !== "#") {
    window.open(url, '_blank');
    console.log("Redirecionando para:", url);
  }
}

let imagemLogo = "#";

function receberImagem(imagem) {
  imagemLogo = imagem;
}

function exibirImagem() {
  const img = document.getElementById("imagemPopup");
  if (img) img.src = imagemLogo;
}


; (() => {
  const style = document.createElement("style")
  style.textContent = `
    `
  document.head.appendChild(style)
  function addFontAwesome() {
    if (!document.querySelector('link[href*="font-awesome"]')) {
      const fontAwesome = document.createElement("link")
      fontAwesome.rel = "stylesheet"
      fontAwesome.href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      document.head.appendChild(fontAwesome)
    }
  }
  function createThemeToggle() {
    const header = document.querySelector("header")
    if (!header) return
    const headerContainer = document.createElement("div")
    headerContainer.className = "header-container"
    const logoContainer = document.createElement("div")
    logoContainer.className = "logo-container"
    const logo = document.querySelector(".img_logo")
    if (logo) {
      logoContainer.appendChild(logo.cloneNode(true))
    }
    const logoText = document.createElement("h1")
    logoText.textContent = "Cupincha"
    logoContainer.appendChild(logoText)
    const mobileMenuToggle = document.createElement("button")
    mobileMenuToggle.className = "mobile-menu-toggle"
    mobileMenuToggle.innerHTML = '<i class="fas fa-bars"></i>'
    mobileMenuToggle.addEventListener("click", toggleMobileMenu)
    const navContainer = document.createElement("div")
    navContainer.className = "nav-container"
    const nav = document.querySelector(".subCabecalho nav")
    if (nav) {
      navContainer.appendChild(nav.cloneNode(true))
    }
    const headerActions = document.createElement("div")
    headerActions.className = "header-actions"
    const searchBtn = document.createElement("button")
    searchBtn.className = "search-btn"
    searchBtn.textContent = "Buscar"
    const themeToggle = document.createElement("button")
    themeToggle.className = "theme-toggle"
    themeToggle.innerHTML = '<i class="fas fa-moon"></i>'
    themeToggle.addEventListener("click", toggleDarkMode)
    headerActions.appendChild(searchBtn)
    headerActions.appendChild(themeToggle)
    headerContainer.appendChild(logoContainer)
    headerContainer.appendChild(mobileMenuToggle)
    headerContainer.appendChild(navContainer)
    headerContainer.appendChild(headerActions)
    header.innerHTML = ""
    header.appendChild(headerContainer)
  }
  function toggleMobileMenu() {
    const navContainer = document.querySelector(".nav-container")
    if (navContainer) {
      navContainer.classList.toggle("active")
      const mobileMenuToggle = document.querySelector(".mobile-menu-toggle")
      if (mobileMenuToggle) {
        if (navContainer.classList.contains("active")) {
          mobileMenuToggle.innerHTML = '<i class="fas fa-times"></i>'
        } else {
          mobileMenuToggle.innerHTML = '<i class="fas fa-bars"></i>'
        }
      }
    }
  }
  function toggleDarkMode() {
    document.body.classList.toggle("dark-mode")
    const isDarkMode = document.body.classList.contains("dark-mode")
    localStorage.setItem("darkMode", isDarkMode)
    const themeToggle = document.querySelector(".theme-toggle")
    if (themeToggle) {
      themeToggle.innerHTML = isDarkMode ? '<i class="fas fa-sun"></i>' : '<i class="fas fa-moon"></i>'
    }
  }
  function loadThemePreference() {
    const isDarkMode = localStorage.getItem("darkMode") === "true"
    if (isDarkMode) {
      document.body.classList.add("dark-mode")
      const themeToggle = document.querySelector(".theme-toggle")
      if (themeToggle) {
        themeToggle.innerHTML = '<i class="fas fa-sun"></i>'
      }
    }
  }
  function restructurePage() {
    const header = document.querySelector("header")
    const subHeader = document.querySelector(".subCabecalho")
    const destaques = document.querySelector(".destaques")
    const cuponsHomePage = document.querySelector(".cuponsHomePage")
    if (!header || !subHeader || !destaques || !cuponsHomePage) return
    createThemeToggle()
    const hero = document.createElement("section")
    hero.className = "hero"
    const heroContent = document.createElement("div")
    heroContent.className = "hero-content"
    const heroTitle = document.createElement("h1")
    heroTitle.textContent = "Economize com cupons exclusivos"
    const heroSubtitle = document.createElement("p")
    heroSubtitle.textContent = "Encontre os melhores cupons de desconto para suas lojas favoritas"
    const ctaButton = document.createElement("button")
    ctaButton.className = "cta-button"
    ctaButton.innerHTML = 'Pegue seu cupom agora <i class="fas fa-arrow-right"></i>'
    heroContent.appendChild(heroTitle)
    heroContent.appendChild(heroSubtitle)
    heroContent.appendChild(ctaButton)
    hero.appendChild(heroContent)
    header.parentNode.insertBefore(hero, header.nextSibling)
    const featuredStores = document.createElement("section")
    featuredStores.className = "featured-stores"
    const featuredContainer = document.createElement("div")
    featuredContainer.className = "container"
    const featuredHeader = document.createElement("div")
    featuredHeader.className = "section-header"
    const featuredTitle = document.createElement("h2")
    featuredTitle.className = "section-title"
    featuredTitle.textContent = "Lojas em Destaque"
    featuredHeader.appendChild(featuredTitle)
    featuredContainer.appendChild(featuredHeader)
    const storesGrid = document.createElement("div")
    storesGrid.className = "stores-grid"
    const storeLogos = document.querySelectorAll(".logoDestaque img")
    storeLogos.forEach((logo) => {
      const storeCard = document.createElement("a")
      storeCard.className = "store-card"
      storeCard.href = logo.closest("a").href
      const storeImg = document.createElement("img")
      storeImg.src = logo.src
      storeImg.alt = logo.alt || "Logo da loja"
      storeCard.appendChild(storeImg)
      storesGrid.appendChild(storeCard)
    })
    featuredContainer.appendChild(storesGrid)
    featuredStores.appendChild(featuredContainer)
    hero.parentNode.insertBefore(featuredStores, hero.nextSibling)
    const couponsSection = document.createElement("section")
    couponsSection.className = "coupons-section section"
    const couponsContainer = document.createElement("div")
    couponsContainer.className = "container"
    const couponsHeader = document.createElement("div")
    couponsHeader.className = "section-header"
    const couponsTitle = document.createElement("h2")
    couponsTitle.className = "section-title"
    couponsTitle.textContent = "Os Melhores Cupons da Semana"
    const couponsDescription = document.createElement("p")
    couponsDescription.className = "section-description"
    couponsDescription.textContent = "Economize em suas compras com nossos cupons exclusivos e verificados"
    couponsHeader.appendChild(couponsTitle)
    couponsHeader.appendChild(couponsDescription)
    couponsContainer.appendChild(couponsHeader)
    const couponsGrid = document.createElement("div")
    couponsGrid.className = "coupons-grid"
    const cupons = document.querySelectorAll(".cupomDesconto")
    cupons.forEach((cupom) => {
      const couponCard = document.createElement("div")
      couponCard.className = "coupon-card"
      const couponHeader = document.createElement("div")
      couponHeader.className = "coupon-header"
      const logoImg = cupom.querySelector(".logoCupom img")
      if (logoImg) {
        const logoContainer = document.createElement("div")
        logoContainer.className = "coupon-logo-container"
        const couponLogo = document.createElement("img")
        couponLogo.className = "coupon-logo"
        couponLogo.src = logoImg.src
        couponLogo.alt = logoImg.alt || "Logo da loja"
        logoContainer.appendChild(couponLogo)
        couponHeader.appendChild(logoContainer)
      }
      const discountText = cupom.querySelector(".h2_desconto")
      if (discountText) {
        const couponDiscount = document.createElement("div")
        couponDiscount.className = "coupon-discount"
        couponDiscount.textContent = discountText.textContent.trim()
        couponHeader.appendChild(couponDiscount)
      }
      couponCard.appendChild(couponHeader)
      const couponBody = document.createElement("div")
      couponBody.className = "coupon-body"
      const titleText = cupom.querySelector(".h2_cupom")
      if (titleText) {
        const couponTitle = document.createElement("h3")
        couponTitle.className = "coupon-title"
        couponTitle.textContent = titleText.textContent.trim()
        couponBody.appendChild(couponTitle)
      }
      const descText = cupom.querySelector(".p_cupom")
      if (descText) {
        const couponDesc = document.createElement("p")
        couponDesc.className = "coupon-description"
        couponDesc.textContent = descText.textContent.trim()
        couponBody.appendChild(couponDesc)
      }
      const validityText = cupom.querySelector(".validade_cupom")
      if (validityText) {
        const couponValidity = document.createElement("p")
        couponValidity.className = "coupon-validity"
        couponValidity.innerHTML = '<i class="far fa-clock"></i> ' + validityText.textContent.trim()
        couponBody.appendChild(couponValidity)
      }
      const button = cupom.querySelector(".pegar_cupom")
      if (button) {
        const couponButton = document.createElement("button")
        couponButton.className = "coupon-button"
        couponButton.innerHTML = '<i class="fas fa-ticket-alt"></i> Ver o Cupom'
        couponButton.setAttribute("onclick", button.getAttribute("onclick"))
        couponBody.appendChild(couponButton)
      }
      couponCard.appendChild(couponBody)
      couponsGrid.appendChild(couponCard)
    })
    couponsContainer.appendChild(couponsGrid)
    couponsSection.appendChild(couponsContainer)
    featuredStores.parentNode.insertBefore(couponsSection, featuredStores.nextSibling)
    const howToUse = document.createElement("section")
    howToUse.className = "how-to-use section"
    const howToUseContainer = document.createElement("div")
    howToUseContainer.className = "container"
    const howToUseHeader = document.createElement("div")
    howToUseHeader.className = "section-header"
    const howToUseTitle = document.createElement("h2")
    howToUseTitle.className = "section-title"
    howToUseTitle.textContent = "Como Usar os Cupons"
    const howToUseDescription = document.createElement("p")
    howToUseDescription.className = "section-description"
    howToUseDescription.textContent = "Economizar com nossos cupons é fácil e rápido. Siga os passos abaixo:"
    howToUseHeader.appendChild(howToUseTitle)
    howToUseHeader.appendChild(howToUseDescription)
    howToUseContainer.appendChild(howToUseHeader)
    const stepsContainer = document.createElement("div")
    stepsContainer.className = "steps-container"
    const steps = [
      {
        icon: "fa-search",
        title: "Encontre o Cupom",
        description: "Navegue pelos cupons disponíveis e encontre o que melhor se adequa à sua compra.",
      },
      {
        icon: "fa-copy",
        title: "Copie o Código",
        description: "Clique em 'Ver o Cupom' e copie o código de desconto apresentado.",
      },
      {
        icon: "fa-shopping-cart",
        title: "Use na Loja",
        description: "Cole o código no campo de cupom durante o checkout na loja online.",
      },
    ]
    steps.forEach((step, index) => {
      const stepCard = document.createElement("div")
      stepCard.className = "step-card"
      const stepIcon = document.createElement("div")
      stepIcon.className = "step-icon"
      stepIcon.innerHTML = `<i class="fas ${step.icon}"></i>`
      const stepTitle = document.createElement("h3")
      stepTitle.className = "step-title"
      stepTitle.textContent = step.title
      const stepDescription = document.createElement("p")
      stepDescription.className = "step-description"
      stepDescription.textContent = step.description
      stepCard.appendChild(stepIcon)
      stepCard.appendChild(stepTitle)
      stepCard.appendChild(stepDescription)
      stepsContainer.appendChild(stepCard)
    })
    howToUseContainer.appendChild(stepsContainer)
    howToUse.appendChild(howToUseContainer)
    couponsSection.parentNode.insertBefore(howToUse, couponsSection.nextSibling)
    const popularStores = document.createElement("section")
    popularStores.className = "popular-stores section"
    const popularStoresContainer = document.createElement("div")
    popularStoresContainer.className = "container"
    const popularStoresHeader = document.createElement("div")
    popularStoresHeader.className = "section-header"
    const popularStoresTitle = document.createElement("h2")
    popularStoresTitle.className = "section-title"
    popularStoresTitle.textContent = "Lojas Populares"
    popularStoresHeader.appendChild(popularStoresTitle)
    popularStoresContainer.appendChild(popularStoresHeader)
    const storeLinks = document.querySelectorAll(".lojaPt2 ul li a")
    const storesList = document.createElement("div")
    storesList.className = "stores-list"
    storeLinks.forEach((link) => {
      const storeLink = document.createElement("a")
      storeLink.className = "store-link"
      storeLink.href = link.href
      storeLink.innerHTML = `<i class="fas fa-store"></i> ${link.textContent}`
      storesList.appendChild(storeLink)
    })
    popularStoresContainer.appendChild(storesList)
    popularStores.appendChild(popularStoresContainer)
    howToUse.parentNode.insertBefore(popularStores, howToUse.nextSibling)
    const popup = document.getElementById("operador")
    if (popup) {
      popup.className = "coupon-popup"
      const overlay = document.createElement("div")
      overlay.className = "overlay"
      document.body.appendChild(overlay)
      const popupHeader = document.createElement("div")
      popupHeader.className = "popup-header"
      const popupTitle = document.createElement("h3")
      popupTitle.className = "popup-title"
      popupTitle.textContent = "Desconto é no Cupincha!"
      const closeBtn = document.createElement("button")
      closeBtn.className = "popup-close"
      closeBtn.innerHTML = '<i class="fas fa-times"></i>'
      closeBtn.onclick = () => {
        popup.style.display = "none"
        overlay.style.display = "none"
      }
      popupHeader.appendChild(popupTitle)
      popupHeader.appendChild(closeBtn)
      const popupBody = document.createElement("div")
      popupBody.className = "popup-body"
      const popupImg = popup.querySelector("#imagemPopup")
      if (popupImg) {
        popupImg.className = "popup-logo"
        popupBody.appendChild(popupImg)
      }
      const codeContainer = document.createElement("div")
      codeContainer.className = "coupon-code-container"
      const codeInput = popup.querySelector("#codigo")
      if (codeInput) {
        codeInput.className = "coupon-code"
        codeContainer.appendChild(codeInput)
        const copyBtn = document.createElement("button")
        copyBtn.className = "copy-button"
        copyBtn.innerHTML = '<i class="fas fa-copy"></i> Copiar'
        copyBtn.onclick = () => {
          codeInput.select()
          document.execCommand("copy")
          copyBtn.innerHTML = '<i class="fas fa-check"></i> Copiado!'
          setTimeout(() => {
            copyBtn.innerHTML = '<i class="fas fa-copy"></i> Copiar'
          }, 2000)
          setTimeout(() => {
            window.open(window.storeLink || "#", "_blank")
          }, 500)
        }
        codeContainer.appendChild(copyBtn)
      }
      popupBody.appendChild(codeContainer)
      const note = document.createElement("p")
      note.className = "popup-note"
      note.innerHTML = '<i class="fas fa-info-circle"></i> Você será redirecionado para a loja!'
      popupBody.appendChild(note)
      popup.innerHTML = ""
      popup.appendChild(popupHeader)
      popup.appendChild(popupBody)
      window.mostrarDiv = () => {
        popup.style.display = "block"
        overlay.style.display = "block"
      }
      window.receberLink = (link) => {
        window.storeLink = link
      }
    }
    const footer = document.querySelector("footer")
    if (footer) {
      const footerContainer = document.createElement("div")
      footerContainer.className = "footer-container"
      const footerLogo = document.createElement("div")
      footerLogo.className = "footer-section"
      const logoImg = document.querySelector(".img_logo")
      if (logoImg) {
        const logoImgClone = logoImg.cloneNode(true)
        footerLogo.appendChild(logoImgClone)
      }
      const footerAbout = document.createElement("p")
      footerAbout.className = "footer-about"
      footerAbout.textContent =
        "O Cupincha é o seu portal de cupons de desconto para as maiores lojas de todo o mundo. Economize em suas compras online!"
      footerLogo.appendChild(footerAbout)
      footerContainer.appendChild(footerLogo)
      const contentSection = document.createElement("div")
      contentSection.className = "footer-section"
      const contentTitle = document.createElement("h3")
      contentTitle.textContent = "Conteúdo"
      contentSection.appendChild(contentTitle)
      const contentLinks = [
        { icon: "fa-ticket-alt", text: "Cupons", url: "#" },
        { icon: "fa-store", text: "Lojas", url: "#" },
        { icon: "fa-newspaper", text: "Artigos", url: "#" },
        { icon: "fa-tag", text: "Ofertas", url: "#" },
        { icon: "fa-percent", text: "Descontos", url: "#" },
      ]
      const contentList = document.createElement("ul")
      contentLinks.forEach((link) => {
        const li = document.createElement("li")
        const a = document.createElement("a")
        a.href = link.url
        a.innerHTML = `<i class="fas ${link.icon}"></i> ${link.text}`
        li.appendChild(a)
        contentList.appendChild(li)
      })
      contentSection.appendChild(contentList)
      footerContainer.appendChild(contentSection)
      const contactSection = document.createElement("div")
      contactSection.className = "footer-section"
      const contactTitle = document.createElement("h3")
      contactTitle.textContent = "Fale Conosco"
      contactSection.appendChild(contactTitle)
      const contactLinks = [
        { icon: "fa-headset", text: "Canais de contato", url: "#" },
        { icon: "fa-envelope", text: "Formulário de atendimento", url: "#" },
      ]
      const contactList = document.createElement("ul")
      contactLinks.forEach((link) => {
        const li = document.createElement("li")
        const a = document.createElement("a")
        a.href = link.url
        a.innerHTML = `<i class="fas ${link.icon}"></i> ${link.text}`
        li.appendChild(a)
        contentList.appendChild(li)
      })
      contactSection.appendChild(contactList)
      footerContainer.appendChild(contactSection)
      const aboutSection = document.createElement("div")
      aboutSection.className = "footer-section"
      const aboutTitle = document.createElement("h3")
      aboutTitle.textContent = "Institucional"
      aboutSection.appendChild(aboutTitle)
      const aboutLinks = [
        { icon: "fa-info-circle", text: "Sobre nós", url: "#" },
        { icon: "fa-briefcase", text: "Carreira", url: "#" },
        { icon: "fa-calendar-alt", text: "Eventos", url: "#" },
        { icon: "fa-shield-alt", text: "Política de Privacidade", url: "#" },
      ]
      const aboutList = document.createElement("ul")
      aboutLinks.forEach((link) => {
        const li = document.createElement("li")
        const a = document.createElement("a")
        a.href = link.url
        a.innerHTML = `<i class="fas ${link.icon}"></i> ${link.text}`
        li.appendChild(a)
        contentList.appendChild(li)
      })
      aboutSection.appendChild(aboutList)
      footerContainer.appendChild(aboutSection)
      const footerBottom = document.createElement("div")
      footerBottom.className = "footer-bottom"
      const socialIcons = document.createElement("div")
      socialIcons.className = "social-icons"
      const socialLinks = [
        { icon: "fa-facebook-f", url: "#" },
        { icon: "fa-twitter", url: "#" },
        { icon: "fa-instagram", url: "#" },
        { icon: "fa-linkedin-in", url: "#" },
        { icon: "fa-youtube", url: "#" },
      ]
      socialLinks.forEach((social) => {
        const a = document.createElement("a")
        a.href = social.url
        a.className = "social-icon"
        a.innerHTML = `<i class="fab ${social.icon}"></i>`
        socialIcons.appendChild(a)
      })
      footerBottom.appendChild(socialIcons)
      const copyright = document.createElement("p")
      copyright.className = "copyright"
      copyright.textContent = `© ${new Date().getFullYear()} Cupincha. Todos os direitos reservados.`
      footerBottom.appendChild(copyright)
      footer.innerHTML = ""
      footer.appendChild(footerContainer)
      footer.appendChild(footerBottom)
    }
    if (subHeader) subHeader.remove()
    if (destaques) destaques.remove()
    if (cuponsHomePage) {
      const tamanhoLimite = cuponsHomePage.querySelector(".tamanhoLimite")
      if (tamanhoLimite) tamanhoLimite.remove()
    }
  }
  function addAnimations() {
    const animateElements = document.querySelectorAll(".coupon-card, .store-card, .step-card, .store-link")
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry, index) => {
          if (entry.isIntersecting) {
            setTimeout(() => {
              entry.target.style.opacity = "1"
              entry.target.style.transform = "translateY(0)"
            }, index * 50)
            observer.unobserve(entry.target)
          }
        })
      },
      { threshold: 0.1 },
    )
    animateElements.forEach((element) => {
      element.style.opacity = "0"
      element.style.transform = "translateY(20px)"
      element.style.transition = "opacity 0.5s, transform 0.5s"
      observer.observe(element)
    })
  }
  function init() {
    addFontAwesome()
    if (document.readyState === "loading") {
      document.addEventListener("DOMContentLoaded", () => {
        restructurePage()
        loadThemePreference()
        setTimeout(addAnimations, 500)
      })
    } else {
      restructurePage()
      loadThemePreference()
      setTimeout(addAnimations, 500)
    }
  }
  init()
})()