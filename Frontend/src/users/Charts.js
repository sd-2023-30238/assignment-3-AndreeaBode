import React, { useState, useEffect } from "react";
import axios from "axios";
import moment from "moment";
import { useParams } from "react-router-dom";
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from "recharts";

const CHART_TYPES = {
  STRESS: "stress",
  DURATION: "duration",
  ENERGY: "energy"
};

const ChartDecorator = (WrappedChart) => ({ chartData, type }) => {
  const data = chartData.map((item) => parseInt(item[type]));
  const average = data.reduce((sum, value) => sum + value, 0) / data.length;
  let color = "red";
  
  if (type === CHART_TYPES.DURATION) {
    color = (average > 4) ? "green" : "red";
  } else if (type === CHART_TYPES.STRESS) {
    color = (average > 2) ? "red" : "green";
  } else if (type === CHART_TYPES.ENERGY) {
    color = (average > 3) ? "green" : "red";
  }

  return <WrappedChart chartData={chartData} type={type} color={color} />;
};

const BarChartWrapper = ({ chartData, type, color }) => {
  const data = chartData.map((item) => parseInt(item[type]));
  const average = data.reduce((sum, value) => sum + value, 0) / data.length;

  return (
      <div>
        <BarChart
          width={500}
          height={300}
          data={chartData}
          margin={{
            top: 4,
            right: 50,
            left: 90,
            bottom: 4,
          }}
          barSize={20}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="data" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Bar dataKey={type} fill={color} background={{ fill: "#eee" }} />
        </BarChart>
      </div>
   
  );
};



const DecoratedBarChart = ChartDecorator(BarChartWrapper);

const WeeklyChartStrategy = ({ chartData }) => (
  <>
    <DecoratedBarChart chartData={chartData} type={CHART_TYPES.DURATION} />
    <DecoratedBarChart chartData={chartData} type={CHART_TYPES.ENERGY} />
    <DecoratedBarChart chartData={chartData} type={CHART_TYPES.STRESS} />
   
  </>
);

const MonthlyChartStrategy = ({ chartData }) => (
  <>
    <DecoratedBarChart chartData={chartData} type={CHART_TYPES.DURATION} />
    <DecoratedBarChart chartData={chartData} type={CHART_TYPES.ENERGY} />
    <DecoratedBarChart chartData={chartData} type={CHART_TYPES.STRESS} />
    
  </>
);

export default function Home() {
  const [users, setUsers] = useState([]);
  const [selectedView, setSelectedView] = useState("weekly");

  const { id } = useParams();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get(
      "http://localhost:8080/api/dreamService/getAllDreams"
    );
    setUsers(result.data);
  };

  const handleViewChange = (view) => {
    setSelectedView(view);
  };

  const startDate = selectedView === "weekly"
    ? moment().startOf("week").format("YYYY-MM-DD")
    : moment().startOf("month").format("YYYY-MM-DD");
  const endDate = selectedView === "weekly"
    ? moment().endOf("week").format("YYYY-MM-DD")
    : moment().endOf("month").format("YYYY-MM-DD");

  const chartData = users
    .filter((user) =>
      moment(user.data).isBetween(startDate, endDate, null, "[]")
    )
    .map((user) => ({
      data: moment(user.data).format("YYYY-MM-DD"),
      duration: user.durata,
      energy: user.energie,
      stress: user.stres,
    }));
    return (
      <div>
        <br />
        <br />
        <br />
        <div className="chart">
          <div>
          <label htmlFor="view-select" class="view-select-label">Select view:</label>
            <select
              id="select"
              value={selectedView}
              onChange={(e) => handleViewChange(e.target.value)}
            >
              <option value="weekly">Weekly</option>
              <option value="monthly">Monthly</option>
            </select>
          </div>
          {selectedView === "weekly" && (
            <WeeklyChartStrategy chartData={chartData} />
          )}
          {selectedView === "monthly" && (
            <MonthlyChartStrategy chartData={chartData} />
          )}
        </div>
      </div>
    ); 

}