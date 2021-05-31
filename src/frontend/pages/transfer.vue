<template>
  <div class="offset-2 col-8">
    <div class="row">
      <div class="form-group col-2">
        <label for="">Destino</label>
        <the-mask
          :mask="'######'"
          v-model="destiny"
          @input="searchDestiny()"
          class="form-control"
        />
        <small
          class="col-2"
          v-text="destiny_user.name"
          v-if="destiny_user"
        ></small>
      </div>

      <div class="form-group col-2">
        <label for="">Valor</label>
        <money v-model="value" class="form-control"></money>
      </div>
      <div class="form-group col-4">
        <label for="">Data Agendamento</label>
        <date-picker
          class="mt-1 w-100"
          v-model="date"
          valueType="format"
          format="DD/MM/YYYY"
        ></date-picker>
      </div>
      <div class="form-group col-4">
        <button
          type="button"
          :class="is_disabled ? 'disabled' : ''"
          :disabled="is_disabled"
          @click="proc_tax()"
          class="mt-4 w-100 btn btn-primary"
        >
          Enviar
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  layout: "dash",
  data() {
    return {
      destiny: null,
      destiny_user: { name: "" },
      value: 0,
      date: null,
      tax_value: 0,
      tax_type: null,
    };
  },
  computed: {
    is_disabled() {
      return this.destiny_user.name == "" || this.value == 0 || this.date == null;
    },
  },
  methods: {
    proc_tax() {
      var date = dayjs(this.date, "DD/MM/YYYY").format("YYYY-MM-DD");
      var data = new FormData();
      data.append("destiny", this.destiny_user.id);
      data.append("value", this.value);
      data.append("scheduling", date);
      this.$axios
        .post("/api/transfers/new", data)
        .then((response) => {
          if (!response.data) {
            alert("Ocorreu um erro ao efetuar a transferencia");
            return false;
          }
          this.$router.push("/hist");
        })
        .catch((r) => {
          alert(r.response.data);
        });
    },
    searchDestiny() {
      this.$axios
        .get("/api/users/account/" + this.destiny)
        .then((r) => {
          this.destiny_user = r.data;
        })
        .catch((r) => {
          alert(r.response.data);
        });
    },
  },
};
</script>
