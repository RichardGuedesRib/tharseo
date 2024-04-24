import "../assets/css/style.css";
import React, { useState, useEffect } from "react";
import Menubar from "../Components/menubar.jsx";
import Chart from "../Components/Chart.jsx";
import Menuwallet from "../Components/Menuwallet.jsx";
import Tabletrade from "../Components/Tabletrade.jsx";
import { Link } from "react-router-dom";

function Home({ user, addressServer, getUser }) {
  const [chartInfo, setChartInfo] = useState([]);
  const [containerOrder, setContainerOrder] = useState(false);
  const [visibleBalance, setVisibleBalance] = useState(false);
  const [price, setPrice] = useState("");
  const [amount, setAmount] = useState("");
  const [sideOperation, setSideOperation] = useState("BUY");
  const [typeOperation, setTypeOperation] = useState("");
  const wallet = user.wallet;
  const [limitAsset, setLimitAsset] = useState(5);
  const [limitActiveTrade, setLimiteActiveTrade] = useState(3);
  const [assetsActiveTrade, setAssetsActiveTrade] = useState([]);
  const [gridData, setGridData] = useState(null);
  const [containerInputGrid, setContainerInputGrid] = useState(false);

  useEffect(() => {
    const intervalId = setInterval(async () => {
      try {
        const res = await fetch(addressServer + "/chart");

        if (!res.ok) {
          throw new Error("Error when get chart info");
        }
        const data = await res.json();
        let chartCandles = [];
        for (let i = 0; i < data.length; i++) {
          const candleRequest = data[i];
          const startTimeInMillis = parseInt(candleRequest.startTime);
          const newCandle = {
            x: new Date(startTimeInMillis),
            y: [
              parseFloat(candleRequest.openPrice),
              parseFloat(candleRequest.highPrice),
              parseFloat(candleRequest.lowPrice),
              parseFloat(candleRequest.closePrice),
            ],
          };
          chartCandles.push(newCandle);
        }
        setChartInfo(chartCandles);
      } catch (error) {
        console.error("Error Chart Resquest", error);
      }
    }, 5000);

    if (wallet) {
      const walletFilter = Array.isArray(wallet)
        ? wallet.slice(0, limitActiveTrade)
        : [];

      setAssetsActiveTrade(walletFilter);
    }

    return () => clearInterval(intervalId);
  }, []);

  const getGridData = (data) => {
    setGridData(data);
  };

  const openOrder = async () => {
    let urlRequest;
    if (amount === "") {
      alert("Digite a quantidade");
    }
    if (typeOperation === "LIMIT") {
      if (price === "") {
        alert("Digite o preço desejado");
      }
      urlRequest = `${addressServer}/tharseo/neworderlimit?user=1&acronym=BNBUSDT&side=${sideOperation}&timeinforce=GTC&quantity=${amount}&price=${price}`;
    } else if (typeOperation === "MARKET") {
      urlRequest = `${addressServer}/tharseo/newordermarketmanual?user=1&acronym=BNBUSDT&side=${sideOperation}&timeinforce=GTC&quantity=${amount}`;
    } else {
      alert("Escolha o lado da operação");
    }

    try {
      const request = await fetch(urlRequest, { method: "POST" });
      if (!request.ok) {
        throw new Error("Error when post order");
      }
      await getUser();
      alert("OK!");
      setContainerOrder(false);
    } catch (error) {
      console.error(`Error Requesting Order:`, error);
    }
  };

  return (
    
    <main className="app-dashboard">
      <section className="container-dashboard">
        <Menubar />

        <aside className="container-dashboard-right">
          {/* Inicio Componente */}

          <section
            className={`container-order ${
              containerOrder ? "active" : "hidden"
            }`}
            id="container_order"
          >
            <span className="title-container-order">Ordem Manual</span>

            <span className="asset-container-order">BNBUSDT</span>

            <aside className="container-side-order">
              <span
                className="container-side-buy"
                onClick={() => setSideOperation("BUY")}
              >
                <span class="material-symbols-outlined">shopping_cart</span>
                <span className="btn-order-buy">BUY</span>
              </span>
              <span
                className="container-side-sell"
                onClick={() => setSideOperation("SELL")}
              >
                <span class="material-symbols-outlined">paid</span>
                <span className="btn-order-sell">SELL</span>
              </span>
            </aside>

            <aside className="container-type-order">
              <span
                className="type-order"
                style={{
                  backgroundColor:
                    typeOperation === "LIMIT" ? "#006BFA" : "#2A2A2A",
                }}
                onClick={() => setTypeOperation("LIMIT")}
              >
                LIMIT
              </span>

              <span
                className="type-order"
                style={{
                  backgroundColor:
                    typeOperation === "MARKET" ? "#006BFA" : "#2A2A2A",
                }}
                onClick={() => setTypeOperation("MARKET")}
              >
                MARKET
              </span>
            </aside>

            <div className="container-inputs-order">
              <label
                className="label-input"
                style={{
                  backgroundColor: typeOperation === "MARKET" ? "#2A2A2A" : "",
                }}
              >
                Price:{" "}
                <input
                  required
                  type="number"
                  name="price"
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
                  className="input-order"
                  disabled={typeOperation === "MARKET"}
                />
              </label>
            </div>
            <div className="container-inputs-order">
              <label className="label-input">
                Amount:{" "}
                <input
                  required
                  type="number"
                  name="amount"
                  value={amount}
                  onChange={(e) => setAmount(e.target.value)}
                  className="input-order"
                />
              </label>
            </div>

            <section className="container-buttons-order">
              <section
                className="btn-type-order"
                onClick={openOrder}
                style={{
                  backgroundColor:
                    sideOperation === "SELL" ? "#E55764" : "#56BC7C",
                }}
              >
                <span
                  class="material-symbols-outlined"
                  style={{ fontSize: 30 }}
                >
                  {sideOperation === "SELL" ? "paid" : "shopping_cart"}
                </span>
                <span className="text-btn-cancel-order">
                  {sideOperation === "SELL" ? "SELL" : "BUY"}
                </span>
              </section>

              <aside
                className="btn-order-cancel"
                onClick={() => setContainerOrder(false)}
              >
                <span
                  class="material-symbols-outlined"
                  style={{ fontSize: 30 }}
                >
                  close
                </span>
                <span className="text-btn-cancel-order">CANCELAR</span>
              </aside>
            </section>
          </section>
          <section className="effect-disable"></section>

          {/* Final Componente */}

          <aside className="container-dashboard-right-top">
            <section className="container-dashboard-right-top-left">
              <span className="text-header-welcome">João</span>
            </section>
            <section className="container-dashboard-right-top-right">
              <section className="container-balance-visible">
                <span
                  className="icon-visible-balance"
                  onClick={() => setVisibleBalance(!visibleBalance)}
                >
                  <span
                    class="material-symbols-outlined"
                    id="icon-visible"
                    style={{ fontSize: 20 }}
                  >
                    {visibleBalance ? "visibility" : "visibility_off"}
                  </span>
                </span>
                <span className="text-balance" id="balance-text">
                  {visibleBalance
                    ? `$ ${wallet
                        .find((item) => item.acronym === "USDTUSDT")
                        .quantity.toFixed(0)}`
                    : "$ -----"}
                </span>
              </section>

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
              <Menuwallet wallet={wallet} limit={limitAsset} />

              <section className="container-button">
                <span
                  className="btn-view-all"
                  id="btnviewall"
                  onClick={showMoreAssets}
                >
                  <span className="text-btn-view-all" id="btn-view-all">
                    Ver Mais
                  </span>
                </span>
              </section>
            </aside>
            <Chart
              data={chartInfo}
              wallet={wallet}
              setContainerOrder={setContainerOrder}
            />
          </aside>

          <aside className="container-dashboard-right-bottom">
            <section className="container-dashboard-right-bottom-top">
              <span className="title-active-trades">Ativar Trade</span>
              <Link to="/trade" className="btn-showmore">
                <span>Ver Todos</span>
              </Link>
            </section>
            <section className="container-dashboard-right-bottom-middle footer-home">
              <Tabletrade
                table={assetsActiveTrade}
                setContainerInputGrid={setContainerInputGrid}
                getGridData={getGridData}
                setGridConfig={user.grids}
                addressServer={addressServer}
                className="show-more"
              />
            </section>
          </aside>
        </aside>
      </section>
    </main>
  );

  function showMoreAssets() {
    setLimitAsset((prevLimit) => prevLimit + 5);
    document
      .getElementsByClassName("container-assets-wallet")[0]
      ?.classList.add("show-more");
  }
}

export default Home;
