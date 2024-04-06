package HR152213.desafiopracticoii

data class DatosDelTicket(
    val numeroTicket: Int,
    val tituloTicket: String,
    val descripcionTicket: String,
    val departamentoUsuario: String,
    val AutorTicket: String,
    val emailTicket: String,
    val fechaCreacionTicket: String,
    val estadoTicket: String,
    val fechaFinalizacionTicket: String
)
