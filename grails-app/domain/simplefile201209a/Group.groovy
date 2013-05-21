package simplefile201209a

class Group {

	String authority

	static mapping = {
                table "ugroup"
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
