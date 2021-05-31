<template>
  <div class="container">
    <div>
      <div class="text-center mt-4">
        <Logo />
      </div>
      <div class="offset-2 col-8">
        <div class="form-group">
          <label for="">Email</label>
          <input type="text" v-model="email" class="form-control" />
        </div>
        <div class="form-group">
          <label for="">Senha</label>
          <input type="password" v-model="password" class="form-control" />
        </div>
        <div class="mt-3 d-flex justify-content-end">
          <a href="#" @click="login()" class="btn btn-primary">
            <span
              class="iconify"
              data-icon="ant-design:login-outlined"
              data-inline="false"
            ></span>
            Entrar
          </a>
        </div>
    
      </div>
    </div>
  </div>
</template>

<script>

import md5 from "md5";

export default {
  data() {
    return {
      email: "felipe@newbgp.com.br",
      password: "123"
    };
  },
  methods: {
    login() {
      var data = new FormData();
      data.append("email", this.email);
      data.append("password", md5(this.password));
      this.$auth.loginWith("local", { data }).then((r) => {
        this.$auth.setUser(r.data);
        this.usuario = r.data;
        this.$router.push("/home");
      });
    },
  },
  mounted() {},
};
</script>

<style>
</style>
