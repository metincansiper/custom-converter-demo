class GroovyIntn {
    void addIntn(java.util.Map props) {
    		com.google.gson.JsonObject template = props.get("template")
    		factoid.model.CustomizableModel model = props.get("model")
    		factoid.util.GsonUtil gsonUtil = new factoid.util.GsonUtil()
    		
    		com.google.gson.JsonObject entityObj = template.get("participant").getAsJsonObject()
    		factoid.model.EntityModel entityModel =  gsonUtil.entityModelFromJsonObj(entityObj)
		
		//factoid.model.XrefModel xrefModel = new XrefModel("GO:0000939", "gene ontology")
		//factoid.model.EntityModel entityModel = new EntityModel("Aurora B", xrefModel, "protein")
		org.biopax.paxtools.model.level3.Protein left = model.physicalEntityFromModel(entityModel)
		org.biopax.paxtools.model.level3.Protein right = model.physicalEntityFromModel(entityModel)
		
		org.biopax.paxtools.model.level3.Transport transport = model.addNewInteraction(Transport.class)
		transport.addLeft(left)
		transport.addRight(right)
		
		transport.addComment("http://www.w3.org/2001/XMLSchema#string\">REPLACED http://pathwaycommons.org/pc12/Transport_e5c5c3064a35275da036764754d82987")
    		
    		//com.google.gson.JsonArray ppts = template.get("participants").getAsJsonArray()
    		//com.google.gson.JsonObject ppt = ppts[0]
    		
    		//java.lang.String name = ppt.get("name")
    		//java.util.List<factoid.model.EntityModel> componentModels = gsonUtil.enityModelsFromJsonArray(ppt.get("components").getAsJsonArray())
    		
    		//model.getOrCreatePhysicalEntity(org.biopax.paxtools.model.level3.Complex.class, name, null, false, componentModels)
    }
}