Metadata Assignment Service
===========================

Spring MVC applications which provides an interface to annotate documents.


Below is a sample for metadata json file
{
	"dissertation":  [
		{
			"label": "dc.hidden",
			"gloss": "Hidden",
			"repeatable": false,
			"readOnly": false,
			"hidden": true,
			"inputType": "TEXT",
			"default": "foo"
		},
		{
			"label": "dc.title",
			"gloss": "Title",
			"repeatable": false,
			"readOnly": false,			
			"inputType": "TEXT"
		},
		{
			"label": "dc.creator",
			"gloss": "Creator",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.contributor.committeechair",
			"gloss": "Committee Chair",
			"repeatable": true,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.contributor.committeeMember",
			"gloss": "Committee Member",
			"repeatable": true,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.date.created",
			"gloss": "Date Created",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.date.issued",
			"gloss": "Date Issued",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.subject.lcsh",
			"gloss": "LoC Subject Terms",
			"repeatable": true,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.subject",
			"gloss": "Subject",
			"repeatable": true,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.description",
			"gloss": "Description",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXTAREA"
		},
		{
			"label": "dc.description.abstract",
			"gloss": "Abstract",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXTAREA"
		},
		{
			"label": "thesis.degree.grantor",
			"gloss": "Grantor",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "dc.language.iso",
			"gloss": "Language",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT",
			"default": "en_US"
		},
		{
			"label": "thesis.degree.name",
			"gloss": "Degree Name",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT"
		},
		{
			"label": "thesis.degree.level",
			"gloss": "Degree Level",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT",
			"default": "Doctoral"
		},
		{
			"label": "dc.publisher",
			"gloss": "Publisher",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT",
			"default": "Texas A&M University"
		},
		{
			"label": "dc.type.material",
			"gloss": "Material",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT",
			"default": "text"
		},
		{
			"label": "dc.type.genre",
			"gloss": "Genre",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT",
			"default": "Dissertation"
		},
		{
			"label": "dc.type",
			"gloss": "Type",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT",
			"default": "Thesis"
		},
		{
			"label": "dc.format.digitalOrigin",
			"gloss": "Digital Origin",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXT",
			"default": "reformatted digital"
		},
		{
			"label": "dc.format.medium",
			"gloss": "Medium",
			"repeatable": false,
			"readOnly": true,
			"hidden": false,
			"inputType": "TEXT",
			"default": "electronic"
		},
		{
			"label": "dc.rights",
			"gloss": "Rights",
			"repeatable": false,
			"readOnly": false,
			"hidden": false,
			"inputType": "TEXTAREA",
			"default": "This student work was part of a retrospective digitization project authorized by the Texas A&M University Libraries. This item is in the public domain."
		}
	],
	
	"thesis":  [
		{
			"label": 
