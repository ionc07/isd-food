<template>
  <div id="page-top">
    <PaymentDialog v-if="$store.state.payment.displayUserPayment"/>
    <ErrorDialog v-if="$store.state.errorDialog"/>
    <CreateOrderDialog v-if="$store.state.orders.createOrderSuccess"/>

    <div id="wrapper">
      <ul
          class="navbar-nav sidebar sidebar-dark accordion"
          id="accordionSidebar"
          style="background-color: #ff9800"
      >
        <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
        >
          <li class="nav-item text-center" @click="$router.history.push('/menu')">
            <span class="nav-link nav-brand mt-2">
              <img width="50px" src="./assets/food-bot.png"/>
              <span class="ml-2">Isd-food</span></span
            >
          </li>
        </a>
        <hr class="sidebar-divider my-0"/>
        <div class="nav-padding" v-if="$store.state.role === 'ROLE_supervisor'">
          <li
              class="nav-item "
              v-for="link in SuperVisorLinks"
              :key="link.text"
          >
            <router-link class="nav-link" :to="link.route">
              <v-icon style="color: white; font-size: 18px">{{
                  link.icon
                }}
              </v-icon>
              <span> {{ link.text }}</span></router-link
            >
            <hr class="sidebar-divider my-0"/>
          </li>
        </div>
        <div class="nav-padding" v-else>
          <li class="nav-item " v-for="link in links" :key="link.text">
            <router-link class="nav-link" :to="link.route">
              <v-icon style="color: white; font-size: 18px">{{
                  link.icon
                }}
              </v-icon>
              <span> {{ link.text }}</span></router-link
            >
            <hr class="sidebar-divider my-0"/>
          </li>
        </div>
      </ul>
      <!-- End of Sidebar -->
      <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
          <nav
              class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
          >
            <!-- Sidebar Toggle (Topbar) -->
            {{ this.$route.name }}
            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">
              <!-- Nav Item - Alerts -->
              <li class="nav-item dropdown no-arrow mx-1">
                <!-- Dropdown - Alerts -->
              </li>

              <!-- Nav Item - User Information -->
              <li class="nav-item dropdown no-arrow">
                <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="userDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                >
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small">{{
                      user
                    }}</span>
                  <img
                      class="img-profile rounded-circle"
                      src="https://source.unsplash.com/QAB-WJcbgJk/60x60"
                  />
                </a>
                <!-- Dropdown - User Information -->
                <div
                    class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="userDropdown"
                >
                  <a class="dropdown-item" href="/edit">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profile
                  </a>
                  <a
                      class="dropdown-item "
                      @click="$store.state.payment.displayUserPayment = true"
                  >
                    <i class="fas fa-credit-card mr-2 text-gray-400"></i>
                    Payment
                  </a>

                  <div class="dropdown-divider"></div>
                  <a
                      class="dropdown-item"
                      href="#"
                      data-toggle="modal"
                      data-target="#logoutModal"
                  >
                    <i
                        class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"
                    ></i>
                    Logout
                  </a>
                </div>
              </li>
            </ul>
          </nav>
          <div class="mx-4">
            <router-view></router-view>
            <v-btn
                class="bg-gray-900 scroll-btn"
                v-scroll="handleScroll"
                v-show="scrollTop"
                fab
                dark
                fixed
                bottom
                right
                color="primary"
                @click="scroll"
            >
              <v-icon>mdi-chevron-up</v-icon>
            </v-btn>
          </div>
        </div>
      </div>
    </div>

    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <div
        class="modal fade"
        id="logoutModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button
                class="close"
                type="button"
                data-dismiss="modal"
                aria-label="Close"
            >
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">
            Select "Logout" below if you are ready to end your current session.
          </div>
          <div class="modal-footer">
            <button
                class="btn btn-secondary"
                type="button"
                data-dismiss="modal"
            >
              Cancel
            </button>
            <a class="btn btn-primary" href="/login">Logout</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/assets/styles/app.css";
import ErrorDialog from "@/components/modal/ErrorDialog";
import CreateOrderDialog from "@/components/modal/CreateOrderDialog";
import PaymentDialog from "@/components/modal/PaymentDialog";
import api from "@/components/backend-api";

export default {
  name: "App",
  components: {ErrorDialog, CreateOrderDialog, PaymentDialog},
  data: () => ({
    scrollTop: false,
    user: null,
    role: localStorage.getItem("role"),
    n: false,
    links: [
      { icon: "fas fa-pizza-slice", text: "Menus", route: "/menu" },
      { icon: "fas fa-history", text: "Orders", route: "/orders" },
    ],

    SuperVisorLinks: [
      { icon: "fas fa-pizza-slice", text: "Food", route: "/menu" },
      { icon: "fas fa-history", text: "My orders", route: "/orders" },
      { icon: "fas fa-users", text: "Users", route: "/users/table" },
      { icon: "fas fa-pizza-slice", text: "All menus", route: "/allmenus" },
      { icon: "fas fa-utensils", text: "Providers", route: "/ProviderList" },
      { icon: "fas fa-history", text: "All orders", route: "/ProviderOrders" },
      {icon: "fas fa-money-check", text: "Payment", route: "/payment"},
    ]
  }),


  methods: {
    handleScroll(event) {
      this.scrollTop = window.scrollY > 300;
    },
    scroll() {
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: "smooth"
      });
    }
  },
  beforeCreate() {
    this.$store.state.role = localStorage.getItem("role");
    api.getUserWithoutId().then(r => {
      this.user = r.data.firstName + " " + r.data.lastName;
    });
  },
  created() {
    window.addEventListener("scroll", this.handleScroll);
  },
  destroyed() {
    window.removeEventListener("scroll", this.handleScroll);
  }
};
</script>
<style>
@import "css/sb-admin-2.min.css";

.color-orange {
  color: #ff9300;
}

.nav-padding li a{
  transition: 0.2s;
}

.nav-padding li a:hover {
  cursor: pointer;
  padding-left: 24px !important;
}

.nav-brand:hover {
  /*padding-left: 16px !important;*/
}
</style>
