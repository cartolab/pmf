#!/bin/bash


# Script to remove title headers from abeille files
#

if [ $# -eq 0 ]
then
	perl -pi -0e 's/<at name="classname">com.jeta.forms.components.border.TitledBorderLabel<\/at>[\s]*<at name="properties">[\s]*<object classname="com.jeta.forms.store.support.PropertyMap">[\s]*<at name="height">/<at name="classname">com.jeta.forms.components.border.TitledBorderLabel<\/at><at name="properties"><object classname="com.jeta.forms.store.support.PropertyMap"><at name="text"\/><at name="height">/g' *.xml
elif  [ $1 = "-h" ] || [ $1 = "--help" ];
then
	echo "\tUsage: $0 [<sole_filename_or_pattern_to_fix>]\n\tIf no filename or pattern is specified, it defaults to *.xml"
else
	perl -pi -0e 's/<at name="classname">com.jeta.forms.components.border.TitledBorderLabel<\/at>[\s]*<at name="properties">[\s]*<object classname="com.jeta.forms.store.support.PropertyMap">[\s]*<at name="height">/<at name="classname">com.jeta.forms.components.border.TitledBorderLabel<\/at><at name="properties"><object classname="com.jeta.forms.store.support.PropertyMap"><at name="text"\/><at name="height">/g' $1
fi

