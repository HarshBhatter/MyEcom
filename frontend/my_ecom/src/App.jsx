import { useEffect } from "react";
import { useLocation } from "react-router-dom";

function App() {
  const { hash } = useLocation();

  useEffect(() => {
    if (hash) {
      const element = document.querySelector(hash);
      if (element) {
        element.scrollIntoView({ behavior: "smooth" });
      }
    }
  }, [hash]); // run whenever hash changes

  return (
    <>
      <div></div>
    </>
  );
}

export default App;
