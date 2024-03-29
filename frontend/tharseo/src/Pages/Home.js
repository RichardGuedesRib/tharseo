import "../assets/css/style.css";
import React from "react";

import Menubar from "../Components/menubar.jsx";
import Chart from "../Components/Chart.jsx";

import Itemasset from "../Components/Itemasset.jsx";
import Rowtableactivetrades from "../Components/Rowtableactivestrade.jsx";
import Icon from "react-crypto-icons";
import Tableactivetrade from "../Components/Tableactivetrade.jsx";

const testetable = [
  {
    symbol: "btc",
    name: "Bitcoin",
    value: "1000",
    balance: "1.56",
    value: "34345",
    performance: "+54",
    trade: "Trade"
  },
  {
    symbol: "ada",
    name: "Cardano",
    value: "1000",
    balance: "1.56",
    value: "34345",
    performance: "+54",
    trade: "Trade"
  },
  {
    symbol: "sol",
    name: "Solana",
    value: "1000",
    balance: "1.56",
    value: "34345",
    performance: "+54",
    trade: "Trade"
  }
]

function Home({ chart }) {
  return (
    <main className="app-dashboard">
      <section className="container-dashboard">
        <Menubar />
        <aside className="container-dashboard-right">
          <aside className="container-dashboard-right-top">
            <section className="container-dashboard-right-top-left">
              <span className="text-header-welcome">Bem Vindo, João</span>
            </section>
            <section className="container-dashboard-right-top-right">
              <span className="icon-notification-header">
                <span
                  class="material-symbols-outlined"
                  style={{ fontSize: 30 }}
                >
                  notifications_unread
                </span>
              </span>
              <span className="text-name-user">Joao</span>
              <span className="avatar-header-user">
                <span
                  class="material-symbols-outlined"
                  style={{ fontSize: 50 }}
                >
                  face
                </span>
              </span>
            </section>
          </aside>
          <aside className="container-dashboard-right-middle">
            <aside className="menu-wallet">
              <section className="container-title-menu-wallet">
                <span className="title-menu-wallet">Ativos em Carteira</span>
              </section>

              <section className="container-assets-wallet">
                <Itemasset
                  symbol="bnb"
                  name="Binance"
                  quantity={"25"}
                  percent="55%"
                />
                <Itemasset
                  symbol="ada"
                  name="Binance"
                  quantity={"25"}
                  percent="55%"
                />
                <Itemasset
                  symbol="btc"
                  name="Binance"
                  quantity={"25"}
                  percent="55%"
                />
                <Itemasset
                  symbol="cro"
                  name="Binance"
                  quantity={"25"}
                  percent="55%"
                />
                <Itemasset
                  symbol="xrp"
                  name="Binance"
                  quantity={"25"}
                  percent="55%"
                />
                {/* <Itemasset symbol="ftm" name="Binance" quantity={"25"} percent="55%"/> */}
              </section>
              <section className="container-button">
                <span className="btn-view-all" id="btnviewall">
                  <span className="text-btn-view-all">Ver Todos</span>
                </span>
              </section>
            </aside>
            <Chart data={chart} />
          </aside>
          <aside className="container-dashboard-right-bottom">
            <section className="container-dashboard-right-bottom-top">
              <span className="title-active-trades">Ativar Trade</span>
            </section>
            <section className="container-dashboard-right-bottom-middle">
             <Tableactivetrade table={testetable}/>
            </section>
            <section className="register-active-trades">
              <aside className="item-active-trades"></aside>
            </section>
          </aside>
        </aside>
      </section>
    </main>
  );
}

export default Home;
