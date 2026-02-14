from Main.MainTask import deserialize
from Dop1.DopTask1 import serialize_toml
from Dop3.DopTask3 import serialize_xml
import yaml
import toml
import time

with open('расписание.yaml', 'r', encoding='utf-8') as f:
    yaml_data = f.read()

start_time = time.time()
for i in range(100):
    result = deserialize(yaml_data)
    xml = serialize_xml(result, "расписание")
xml_time = time.time() - start_time

print('yaml to xml time:',xml_time)

start_time = time.time()
for i in range(100):
    result = deserialize(yaml_data)
    toml_output = serialize_toml(result)
toml_time_1 = time.time() - start_time

print('yaml to toml time:',toml_time_1)

start_time = time.time()
for i in range(100):
    result_yaml = yaml.safe_load(yaml_data)
    result_toml = toml.dumps(result_yaml)
toml_time_2 = time.time() - start_time

print('yaml to toml with libs time:',toml_time_2)

