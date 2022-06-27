Minimal Docker sample

gcc prog.c --static -o prog

docker build -t=minimaldemo .

docker run minimaldemo