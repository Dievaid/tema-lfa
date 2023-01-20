cd ./checker/tests

for file in *.in
do
	echo "$file 200.0" >> ../../mytime.cfg
done

cd ../../
