import yaml
import toml

with open('Data/расписание.yaml', 'r', encoding='utf-8') as f:
    yaml_data = yaml.safe_load(f)

with open('Data/MainTaskResult_2.txt', 'w', encoding='utf-8') as f:
    f.write(str(yaml_data))

with open('Data/расписание_2.toml', 'w', encoding='utf-8') as f:
    toml.dump(yaml_data, f)

print(yaml_data)

