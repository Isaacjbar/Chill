import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import {Dashboard} from "./pages/dashboard"
import Normal from "./pages/normal"
import {Intermediate} from "./pages/intermediate"
import {Hard} from "./pages/hard"
function App() {

  return (
    <Router>
      <Routes>
        <Route index path="/" element={<Dashboard />} />
        <Route path="/1/normal" element={<Normal />} />
        <Route path="/1/intermediate" element={<Intermediate />} />
        <Route path="/hard" element={<Hard />} />
      </Routes>
    </Router>
  )
}

export default App
