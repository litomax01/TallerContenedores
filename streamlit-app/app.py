import streamlit as st
import requests
import pandas as pd
import plotly.express as px

API_URL = "http://media-backend:8081/api/media"

st.set_page_config(
    page_title="Media Dashboard",
    layout="wide"
)

st.title("📊 Dashboard de Medios")
st.caption("Streamlit + Spring Boot + MySQL + Docker")

@st.cache_data(ttl=10)
def cargar_datos():
    response = requests.get(API_URL, timeout=10)
    response.raise_for_status()
    return pd.DataFrame(response.json())

try:
    df = cargar_datos()
except Exception as e:
    st.error(f"No se pudo conectar al backend: {e}")
    st.stop()

st.subheader("📋 Registros en la base de datos")

if df.empty:
    st.warning("No hay datos registrados todavía.")
    st.stop()

st.dataframe(df, use_container_width=True)

st.subheader("📌 Indicadores")

col1, col2, col3 = st.columns(3)
col1.metric("Total registros", len(df))
col2.metric("Promedio calificación", round(df["calificacion"].mean(), 2))
col3.metric("Año más reciente", int(df["anio"].max()))

st.subheader("📊 Cantidad por tipo")

fig_tipo = px.bar(df, x="tipo", title="Cantidad de medios por tipo")
st.plotly_chart(fig_tipo, use_container_width=True)

st.subheader("📊 Distribución por estado")

fig_estado = px.pie(df, names="estado", title="Distribución por estado")
st.plotly_chart(fig_estado, use_container_width=True)

st.subheader("📈 Medios por año")

fig_anio = px.histogram(df, x="anio", nbins=20, title="Medios por año")
st.plotly_chart(fig_anio, use_container_width=True)

st.success("✅ Datos cargados correctamente desde MySQL")
