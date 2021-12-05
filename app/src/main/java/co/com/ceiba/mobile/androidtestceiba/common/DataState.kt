package co.com.ceiba.mobile.androidtestceiba.common

/**
 * Modelo de datos para el estado de carga de cualquier objeto
 * @param isLoading Para identificar si se esta o no cargando los datos
 * @param error Muestra el error que ocurrió en la adquisición de los datos
 * @param posts Contiene los datos que fueron cargados desde la BD
 */
data class DataState<T>(
  val isLoading: Boolean = false,
  val posts: T? = null,
  val error: String = ""
)
