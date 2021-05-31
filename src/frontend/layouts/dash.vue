<template>
  <div class="bg-light" id="main">
    <vue-navigation-bar :options="navbarOptions" />
    <div class="container p-4 bg-white mt-4">
      <Nuxt />
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      usuario: null,
      navbarOptions: {
        elementId: "main-navbar",
        isUsingVueRouter: true,
        mobileBreakpoint: 992,
        brandImagePath: "./home",
        brandImage: require("../static/logo.jpg"),
        brandImageAltText: "brand-image",
        collapseButtonOpenColor: "#661c23",
        collapseButtonCloseColor: "#661c23",
        showBrandImageInMobilePopup: true,
        ariaLabelMainNav: "Main Navigation",
        tooltipAnimationType: "shift-away",
        tooltipPlacement: "bottom",
        menuOptionsLeft: [
          {
            type: "link",
            text: "Nova Transferencia",
            path: "/transfer",
          },
          {
            type: "link",
            text: "Histórico",
            path: "/hist",
          },
        ],
        menuOptionsRight: [
          {
            type: "link",
            text: "",
            path: "/home",
          },
          {
            type: "button",
            text: "Sair",
            path: "/",
            class: "btn-danger",
          },
        ],
      },
    };
  },
  mounted() {
    setInterval(() => {
      if (!this.$auth.loggedIn) this.$router.push("/");
      else this.usuario = this.$auth.user;
    }, 100);

    this.navbarOptions.menuOptionsRight[0].text = `${this.$auth.user.name} - ${this.$auth.user.email}`;
    $("#main").height($(window).height());
    /*
    if (this.usuario.privilege > 1)
      this.navbarOptions.menuOptionsLeft.push({
        type: "link",
        text: "Taxas",
        path: "/tax",
      });
      */
  },
};
</script>

<style lang="less" scoped>
.vnb .button-red {
  background: #ff3b30;
}

.vnb .button-red:hover {
  background: #fc0d00;
}
.vnb {
  .button-red {
    background: #ff3b30;

    &:hover {
      background: darken(#ff3b30, 10%);
    }
  }
}
</style>