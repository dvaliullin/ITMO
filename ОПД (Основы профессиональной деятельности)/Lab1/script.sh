#!/bin/sh

# 1.

cd ~
mkdir lab0
cd lab0
mkdir munchlax5
cd munchlax5
mkdir beheeyem
mkdir spinarak
# cat > accelgor
# Способности  Acid Spray Double Team Quick Attack Struggle
# Bug Mega Drain Swift Me First Agility Giga Drain U-turn Bug Buzz
# Recover Power Swap Final Gambit
touch accelgor
echo 'Способности  Acid Spray Double Team Quick Attack Struggle
Bug Mega Drain Swift Me First Agility Giga Drain U-turn Bug Buzz
Recover Power Swap Final Gambit' > accelgor
cd ..
# cat > octillery0
# Способности  Constrict
# Psybeam Aurora Beam Bubblebeam Focus Energy Octazooka Wring Out Signal
# Beam Ice Beam Bullet Seed Hydro Pump Hyper Beam Soak
touch octillery0
echo 'Способности  Constrict
Psybeam Aurora Beam Bubblebeam Focus Energy Octazooka Wring Out Signal
Beam Ice Beam Bullet Seed Hydro Pump Hyper Beam Soak' > octillery0
# cat > poliwrath2
# Тип
# покемона  WATERFIGHTING
touch poliwrath2
echo 'Тип
покемона  WATERFIGHTING' > poliwrath2
# cat > sentret4
# Возможности  Overland=5 Surface=5
# Jump=3 Power=1 Intelligence=3
touch sentret4
echo 'Возможности  Overland=5 Surface=5
Jump=3 Power=1 Intelligence=3' > sentret4
mkdir shinx2
cd shinx2
# cat > whismur
# Живет
# Cave
touch whismur
echo 'Живет
Cave' > whismur
# cat > mantyke
# Возможности  Overland=1 Surface=8 Underwater=8 Jump=4
# Power=2 Intelligence=2 Gilled=0
touch mantyke
echo 'Возможности  Overland=1 Surface=8 Underwater=8 Jump=4
Power=2 Intelligence=2 Gilled=0' > mantyke
# cat > charmander
# Тип покемона  FIRE
# NONE
touch charmander
echo 'Тип покемона  FIRE
NONE' > charmander
# cat > omastar
# Развитые способности  WeakArmor
touch omastar
echo 'Развитые способности  WeakArmor' > omastar
cd ..
mkdir whiscash6
cd whiscash6
mkdir medicham
# cat > serperior
# Тип покемона
# GRASSNONE
touch serperior
echo 'Тип покемона
GRASSNONE' > serperior
mkdir charmander
mkdir reuniclus
# cat > sandshrew
# satk=2 sdef=3 spd=4
touch sandshrew
echo 'satk=2 sdef=3 spd=4' > sandshrew
# cat > dodrio
# weigth=187.8
# height=71.0 atk=11 def=7
touch dodrio
echo 'weigth=187.8
height=71.0 atk=11 def=7' > dodrio

# 2.

cd ..
chmod 311 munchlax5
cd munchlax5
chmod 335 beheeyem
chmod 500 spinarak
chmod 664 accelgor
cd ..
chmod 400 octillery0
chmod 624 poliwrath2
chmod 060 sentret4
chmod 777 shinx2
cd shinx2
chmod 046 whismur
chmod 062 mantyke
chmod 062 charmander
chmod 064 omastar
cd ..
chmod 373 whiscash6
cd whiscash6
chmod 577 medicham
chmod 622 serperior
chmod 737 charmander
chmod 312 reuniclus
chmod 004 sandshrew
chmod 006 dodrio

# 3.

cd ..
cp octillery0 shinx2/whismuroctillery
cp poliwrath2 whiscash6/charmander
cp -r shinx2 whiscash6/reuniclus
# Ошибка
# cp: shinx2/whismur: Permission denied
# cp: shinx2/omastar: Permission denied
# cp: shinx2/mantyke: Permission denied
# cp: shinx2/charmander: Permission denied
# Нет прав на выполнение операции
chmod -R u+r shinx2  # Разрешение владельцу читать файлы каталога shinx2
cp -r shinx2 whiscash6/reuniclus
ln -s whiscash6 Copy_73
cat whiscash6/dodrio shinx2/omastar > sentret4_13
# Ошибка
# cat: whiscash6/dodrio: Permission denied
# Нет прав на выполнение операции
chmod u+r whiscash6/dodrio  # Разрешение владельцу читать файл
cat whiscash6/dodrio shinx2/omastar > sentret4_13
chmod u-r whiscash6/dodrio
ln sentret4 whiscash6/dodriosentret
ln -s poliwrath2 shinx2/charmanderpoliwrath

# 4.

cd whiscash6
wc -m serperior sandshrew dodrio > /tmp/error_lab0 2>&1
cd ..
ls -ltR . | grep "r$" 2> /tmp/error_lab0
grep -vi "Ju" shinx2/omastar whiscash6/serperior whiscash6/sandshrew 2> /tmp/error_lab0
wc -l shinx2/whismur shinx2/mantyke shinx2/charmander shinx2/omastar whiscash6/serperior whiscash6/sandshrew 2> /tmp/error_lab0 | sort -n
ls -lRi . | grep "m$" | sort -k2 -rn | tail -3
ls -lRt . 2>&1 | head -3

# 5.

rm poliwrath2
rm -rf shinx2/whismur
rm shinx2/charmanderpoliwrath
rm -rf whiscash6/dodriosentret
chmod -R u+r whiscash6
rm -rf whiscash6
rmdir munchlax5/spinarak
