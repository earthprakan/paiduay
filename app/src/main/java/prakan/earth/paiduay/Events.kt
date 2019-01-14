package prakan.earth.paiduay

data class Events(
    var id: String,
    var type: String,
    var public: Boolean,
    var created_at: String,
    var payload: Map<String, Any>,
    var repo: Map<String, String>,
    var actor: Map<String, String>
)